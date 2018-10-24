package it.caoxin.Concurrency.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @描述
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Controller
@RequestMapping("/threadlocal")
public class ThreadLocalController {
    @RequestMapping("/test")
    @ResponseBody
    public Long test() {
        return RequestHolder.get();
    }

}
