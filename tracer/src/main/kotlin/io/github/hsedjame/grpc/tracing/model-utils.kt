package io.github.hsedjame.grpc.tracing



fun updateSpans(trace:Trace, f : (List<Span>) -> List<Span>) : Trace =
    trace.copy(spans = f(trace.spans))