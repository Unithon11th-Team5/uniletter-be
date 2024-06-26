package unithon.uniletter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unithon.uniletter.log.LogFilter;

@Configuration
@RequiredArgsConstructor
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<LogFilter> myFilterRegistration() {
        FilterRegistrationBean<LogFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new LogFilter());
        // 모든 URL에 필터 적용
        registration.addUrlPatterns("/login/*");
        registration.addUrlPatterns("/events/*");
        registration.addUrlPatterns("/messages/*");
        registration.setOrder(1); // 필터 순서 지정 (optional)
        return registration;
    }
}
