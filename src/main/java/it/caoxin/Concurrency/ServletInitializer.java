package it.caoxin.Concurrency;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @描述
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ConcurrencyApplication.class);
    }
}
