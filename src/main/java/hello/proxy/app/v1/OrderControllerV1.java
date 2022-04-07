package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 스프링은 @Controller or @RequestMapping 이 있어야 스프링 컨트롤러로 인식
 */
@RequestMapping
@ResponseBody
public interface OrderControllerV1 {

    /**
     * 자바 버전에 따라 컴파일시 인터페이스에 존재 하는 매개변수를 인식하지 못할 수도 있기 때문에 애노테이션을 명시해야한다.
     */
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
