/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.nop.stream.cep.functions.adaptors;

import io.nop.stream.cep.PatternFlatSelectFunction;
import io.nop.stream.cep.PatternFlatTimeoutFunction;
import io.nop.stream.cep.functions.PatternProcessFunction;
import io.nop.stream.cep.functions.TimedOutPartialMatchHandler;
import io.nop.stream.core.configuration.Configuration;
import io.nop.stream.core.util.Collector;
import io.nop.stream.core.util.FunctionUtils;
import io.nop.stream.core.util.OutputTag;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Adapter that expresses combination of {@link PatternFlatSelectFunction} and {@link
 * PatternTimeoutFlatSelectAdapter} with {@link PatternProcessFunction}.
 */
public class PatternTimeoutFlatSelectAdapter<IN, OUT, T> extends PatternFlatSelectAdapter<IN, OUT>
        implements TimedOutPartialMatchHandler<IN> {

    private final PatternFlatTimeoutFunction<IN, T> flatTimeoutFunction;
    private final OutputTag<T> timedOutPartialMatchesTag;

    private transient SideCollector<T> sideCollector;

    public PatternTimeoutFlatSelectAdapter(
            PatternFlatSelectFunction<IN, OUT> flatSelectFunction,
            PatternFlatTimeoutFunction<IN, T> flatTimeoutFunction,
            OutputTag<T> timedOutPartialMatchesTag) {
        super(flatSelectFunction);
        this.flatTimeoutFunction = checkNotNull(flatTimeoutFunction);
        this.timedOutPartialMatchesTag = checkNotNull(timedOutPartialMatchesTag);
    }

    @Override
    public void open(Configuration parameters) {
        super.open(parameters);
        FunctionUtils.setFunctionRuntimeContext(flatTimeoutFunction, getRuntimeContext());
        FunctionUtils.openFunction(flatTimeoutFunction, parameters);

        if (sideCollector == null) {
            sideCollector = new SideCollector<>(checkNotNull(timedOutPartialMatchesTag));
        }
    }

    @Override
    public void close() {
        super.close();
        FunctionUtils.closeFunction(flatTimeoutFunction);
    }

    @Override
    public void processTimedOutMatch(Map<String, List<IN>> match, Context ctx) throws Exception {
        sideCollector.setCtx(ctx);
        flatTimeoutFunction.timeout(match, ctx.timestamp(), sideCollector);
    }

    /**
     * Adapter that emitting timed out results from {@link PatternFlatTimeoutFunction}, which
     * expects {@link Collector} through the {@link Context} of enclosing
     * {@link PatternProcessFunction}.
     */
    private static final class SideCollector<T> implements Collector<T> {

        private final OutputTag<T> timedOutPartialMatchesTag;

        private transient Context ctx;

        private SideCollector(OutputTag<T> timedOutPartialMatchesTag) {
            this.timedOutPartialMatchesTag = checkNotNull(timedOutPartialMatchesTag);
        }

        public void setCtx(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void collect(T record) {
            ctx.output(timedOutPartialMatchesTag, record);
        }

        @Override
        public void close() {
        }
    }
}
