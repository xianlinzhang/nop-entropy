/**
 * Copyright (c) 2017-2024 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-entropy
 * Github: https://github.com/entropy-cloud/nop-entropy
 */
package io.nop.batch.jdbc.consumer;

import io.nop.batch.core.IBatchConsumer;
import io.nop.core.lang.sql.SQL;
import io.nop.core.reflect.bean.BeanTool;
import io.nop.dao.dialect.IDialect;
import io.nop.dao.jdbc.IJdbcTemplate;
import io.nop.dao.jdbc.JdbcBatcher;
import io.nop.dataset.binder.IDataParameterBinder;

import java.util.List;
import java.util.Map;

public class JdbcUpdateBatchConsumer<S, C> implements IBatchConsumer<S, C> {
    private final IJdbcTemplate jdbcTemplate;
    private final IDialect dialect;
    private final String tableName;
    private final List<String> keyFields;

    private final Map<String, IDataParameterBinder> colBinders;

    public JdbcUpdateBatchConsumer(IJdbcTemplate jdbcTemplate, IDialect dialect, String tableName,
                                   List<String> keyFields,
                                   Map<String, IDataParameterBinder> colBinders) {
        this.jdbcTemplate = jdbcTemplate;
        this.dialect = dialect;
        this.tableName = tableName;
        this.keyFields = keyFields;
        this.colBinders = colBinders;
    }

    @Override
    public void consume(List<S> items, C context) {
        SQL sql = SQL.begin().name("batch-update").insertInto(tableName).end();

        jdbcTemplate.runWithConnection(sql, conn -> {
            JdbcBatcher batcher = new JdbcBatcher(conn, dialect, jdbcTemplate.getDaoMetrics());
            for (S item : items) {
                SQL itemSql = buildSql(item);
                batcher.addCommand(itemSql, false, null);
            }
            batcher.flush();
            return null;
        });
    }

    SQL buildSql(S record) {
        SQL.SqlBuilder sb = SQL.begin().name("update:" + tableName);
        sb.update(tableName);
        sb.set();
        boolean first = true;
        for (String colName : colBinders.keySet()) {
            if (!BeanTool.hasProperty(record, colName))
                continue;

            if (keyFields.contains(colName))
                continue;

            if (first) {
                first = false;
            } else {
                sb.append(',');
            }
            Object value = BeanTool.getProperty(record, colName);
            sb.eq(colName, value);
        }
        sb.where();
        first = true;
        for (String keyField : keyFields) {
            if (first) {
                first = false;
            } else {
                sb.append(',');
            }

            IDataParameterBinder binder = this.colBinders.get(keyField);
            appendParam(sb, binder, BeanTool.getProperty(record, keyField));
        }
        sb.append(')');
        return sb.end();
    }

    void appendParam(SQL.SqlBuilder sb, IDataParameterBinder binder, Object value) {
        value = binder.getStdDataType().convert(value);
        sb.typeParam(binder, value, false);
    }
}