package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;

        /*
        예외 처리를 위해서 try-catch 사용, 구현하지 않으면 `orderService.orderItem()`에서 예외를 잡아 먹어 end()가 실행되지 않을 수 있기 때문이다.
         */
        try {
            status = trace.begin(message);
            //로직 호출
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e) {

            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
