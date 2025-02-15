/**
 * Copyright (c) 2017-2024 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-entropy
 * Github: https://github.com/entropy-cloud/nop-entropy
 */
package io.nop.record.model;

import io.nop.commons.collections.bit.IBitSet;
import io.nop.commons.text.SimpleTextTemplate;
import io.nop.commons.util.StringHelper;
import io.nop.record.codec.IFieldBinaryCodec;
import io.nop.record.codec.IFieldTagBinaryCodec;
import io.nop.record.codec.IFieldTagTextCodec;
import io.nop.record.codec.IFieldTextCodec;
import io.nop.record.model._gen._RecordFieldMeta;
import io.nop.xlang.xmeta.ISchema;

import java.util.Map;

public class RecordFieldMeta extends _RecordFieldMeta implements IRecordFieldsMeta {
    private IFieldTextCodec resolvedTextCodec;
    private IFieldBinaryCodec resolvedBinaryCodec;

    private IFieldTagBinaryCodec resolvedTagBinaryCodec;
    private IFieldTagTextCodec resolvedTagTextCodec;

    private SimpleTextTemplate normalizedTemplate;

    public RecordFieldMeta() {

    }

    public void init(RecordDefinitions defs) {
        if (getEndian() == null && defs.getDefaultEndian() != null) {
            setEndian(defs.getDefaultEndian());
        }

        if (getTemplate() != null) {
            this.normalizedTemplate = SimpleTextTemplate.of(StringHelper.normalizeTemplate(getTemplate()));
        }

        for (RecordFieldMeta field : getFields()) {
            field.init(defs);
        }
    }

    public String getTypeByCaseValue(String caseValue) {
        Map<String, String> typeMap = getSwitchTypeMap();
        if (typeMap == null)
            return null;
        String type = typeMap.get(caseValue);
        if (type == null)
            type = typeMap.get("*");
        return type;
    }

    public boolean isMatchTag(IBitSet tags) {
        int tagIndex = getTagIndex();
        if (tagIndex < 0)
            return true;

        if (tags == null)
            return true;

        return tags.get(tagIndex);
    }

    public SimpleTextTemplate getNormalizedTemplate() {
        return this.normalizedTemplate;
    }

    public String getPropOrFieldName() {
        String propName = getProp();
        if (propName == null)
            propName = getName();
        return propName;
    }

    public IFieldTextCodec getResolvedTextCodec() {
        return resolvedTextCodec;
    }

    public void setResolvedTextCodec(IFieldTextCodec resolvedTextCodec) {
        this.resolvedTextCodec = resolvedTextCodec;
    }

    public IFieldTagBinaryCodec getResolvedTagBinaryCodec() {
        return resolvedTagBinaryCodec;
    }

    public void setResolvedTagBinaryCodec(IFieldTagBinaryCodec resolvedTagBinaryCodec) {
        this.resolvedTagBinaryCodec = resolvedTagBinaryCodec;
    }

    public IFieldTagTextCodec getResolvedTagTextCodec() {
        return resolvedTagTextCodec;
    }

    public void setResolvedTagTextCodec(IFieldTagTextCodec resolvedTagTextCodec) {
        this.resolvedTagTextCodec = resolvedTagTextCodec;
    }

    public IFieldBinaryCodec getResolvedBinaryCodec() {
        return resolvedBinaryCodec;
    }

    public void setResolvedBinaryCodec(IFieldBinaryCodec resolvedBinaryCodec) {
        this.resolvedBinaryCodec = resolvedBinaryCodec;
    }

    public int safeGetMaxLen() {
        ISchema schema = getSchema();
        if (schema != null && schema.getMaxLength() != null)
            return schema.getMaxLength();
        return getLength();
    }

    public int safeGetMinLen() {
        ISchema schema = getSchema();
        if (schema != null && schema.getMinLength() != null)
            return schema.getMinLength();
        return getLength();
    }
}
