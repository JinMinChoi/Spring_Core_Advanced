package hello.advanced.app.v2;

import hello.advanced.trace.helloTrace.HelloTraceV2;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;

        /*
        예외 처리를 위해서 try-catch 사용, 구현하지 않으면 `orderService.orderItem()`에서 예외를 잡아 먹어 end()가 실행되지 않을 수 있기 때문이다.
         */
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 꼭 재차 던져야 한다.
        }
    }
}
