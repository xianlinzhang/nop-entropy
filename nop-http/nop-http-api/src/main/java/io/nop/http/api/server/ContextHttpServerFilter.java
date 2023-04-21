package io.nop.http.api.server;

import io.nop.api.core.ApiConstants;
import io.nop.api.core.context.ContextProvider;
import io.nop.api.core.context.IContext;
import io.nop.api.core.util.ApiStringHelper;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

public class ContextHttpServerFilter implements IHttpServerFilter {

    @Override
    public int order() {
        return HIGH_PRIORITY;
    }

    @Override
    public CompletionStage<Void> filterAsync(IHttpServerContext context, Supplier<CompletionStage<Void>> next) {
        String traceId = context.getRequestStringHelper(ApiConstants.MDC_NOP_TRACE);
        String tenantId = context.getRequestStringHelper(ApiConstants.HEADER_TENANT);

        String timezone = context.getRequestStringHelper(ApiConstants.HEADER_TIMEZONE);
        String locale = context.getRequestStringHelper(ApiConstants.HEADER_LOCALE);

        // 前台主动要求限制服务超时时间
        long expireTime = context.getRequestLongHeader(ApiConstants.HEADER_TIMEOUT, -1L);

        IContext ctx = ContextProvider.newContext();
        ctx.setTimezone(timezone);
        ctx.setLocale(locale);
        ctx.setTenantId(tenantId);

        if (ApiStringHelper.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
            ctx.setTraceId(traceId);
        }

        if (expireTime > 0)
            ctx.setCallExpireTime(expireTime);

        MDC.put(ApiConstants.MDC_NOP_TRACE, traceId);
        try {
            return next.get().whenComplete((r, e) -> {
                ctx.close();
            });
        } finally {
            MDC.remove(ApiConstants.MDC_NOP_TRACE);
        }
    }
}