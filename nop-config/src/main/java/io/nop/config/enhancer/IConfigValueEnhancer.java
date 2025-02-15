/**
 * Copyright (c) 2017-2024 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-entropy
 * Github: https://github.com/entropy-cloud/nop-entropy
 */
package io.nop.config.enhancer;

import io.nop.api.core.config.IConfigValue;

public interface IConfigValueEnhancer {
    <T> IConfigValue<T> enhance(Object value, Class<T> targetClass);
}