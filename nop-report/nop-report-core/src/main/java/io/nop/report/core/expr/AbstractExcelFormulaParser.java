package io.nop.report.core.expr;

import io.nop.api.core.util.SourceLocation;
import io.nop.commons.text.tokenizer.TextScanner;
import io.nop.core.model.table.CellPosition;
import io.nop.core.model.table.CellRange;
import io.nop.core.model.table.utils.CellReferenceHelper;
import io.nop.report.core.coordinate.CellCoordinate;
import io.nop.report.core.coordinate.CellLayerCoordinate;
import io.nop.xlang.ast.CustomExpression;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.Identifier;
import io.nop.xlang.expr.simple.SimpleExprParser;

import java.util.ArrayList;
import java.util.List;

import static io.nop.report.core.XptErrors.ARG_CELL_POS;
import static io.nop.report.core.XptErrors.ERR_XPT_INVALID_CELL_RANGE_EXPR;

public class AbstractExcelFormulaParser extends SimpleExprParser {
    @Override
    protected Expression varFactorExpr(TextScanner sc) {
        Identifier id = tokenExpr(sc);
        if (CellReferenceHelper.isABString(id.getName())) {
            if (sc.cur == ':') {
                return cellRangeExpr(sc, id);
            }
            return cellCoordinateExpr(sc, id);
        }
        return arrowFuncExpr(sc, id);
    }
    public CustomExpression parseCellExpr(TextScanner sc) {
        Identifier id = tokenExpr(sc);
        if (sc.cur == ':') {
            return cellRangeExpr(sc, id);
        }
        return cellCoordinateExpr(sc, id);
    }
    public CellLayerCoordinate parseLayerCoordinate(SourceLocation loc, String text) {
        TextScanner sc = TextScanner.fromString(loc, text);
        sc.skipBlank();
        Identifier id = tokenExpr(sc);
        CellLayerCoordinate layerCoordinate = cellCoordinate(sc, id);
        sc.checkEnd();
        return layerCoordinate;
    }

    protected CustomExpression cellRangeExpr(TextScanner sc, Identifier id) {
        sc.consume(':');
        Identifier end = tokenExpr(sc);
        if (!CellReferenceHelper.isABString(id.getName()))
            throw sc.newError(ERR_XPT_INVALID_CELL_RANGE_EXPR)
                    .param(ARG_CELL_POS, end.getName());

        CellPosition first = CellPosition.fromABString(id.getName());
        CellPosition last = CellPosition.fromABString(end.getName());
        CellRange range = CellRange.fromPosition(first, last);
        SourceLocation loc = id.getLocation();
        return CustomExpression.build(loc, range.toString(), new CellRangeExecutable(loc, range));
    }

    protected CustomExpression cellCoordinateExpr(TextScanner sc, Identifier id) {
        SourceLocation loc = sc.location();
        CellLayerCoordinate layerCoordinate = cellCoordinate(sc, id);
        return CustomExpression.build(loc, layerCoordinate.toString(), new CellLayerCoordinateExecutable(loc, layerCoordinate));
    }

    private CellLayerCoordinate cellCoordinate(TextScanner sc, Identifier id) {
        //SourceLocation loc = sc.location();
        CellLayerCoordinate layerCoord = new CellLayerCoordinate();
        String cellName = id.getName();
        layerCoord.setCellName(cellName);

        if (sc.tryMatch('[')) {
            List<CellCoordinate> rowCoordinates = parseCellCoordinates(sc);
            List<CellCoordinate> colCoordinates = null;
            if (sc.tryMatch(';')) {
                colCoordinates = parseCellCoordinates(sc);
            }
            layerCoord.setRowCoordinates(rowCoordinates);
            layerCoord.setColCoordinates(colCoordinates);
            sc.match(']');
        }

        return layerCoord;
    }

    private List<CellCoordinate> parseCellCoordinates(TextScanner sc) {
        List<CellCoordinate> ret = new ArrayList<>();
        while (sc.cur != ';' && sc.cur != ']') {
            CellCoordinate coord = new CellCoordinate();
            String cellName = sc.nextJavaVar();
            coord.setCellName(cellName);
            if (sc.tryMatch(':')) {
                if (sc.tryMatch('!')) {
                    coord.setReverse(true);
                }
                if (sc.tryMatch('+')) {
                    coord.setRelative(true);
                }
                int pos = sc.nextInt();
                if (pos < 0) {
                    coord.setRelative(true);
                }
                coord.setPosition(pos);
            }
            ret.add(coord);
            if (!sc.tryMatch(',')) {
                break;
            }
        }
        return ret.isEmpty() ? null : ret;
    }
}