package com.unimon.app.common.configurer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.unimon.app.common.interceptor.AuthInterceptor;
import com.unimon.app.common.interceptor.SessionInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final String[] INCLUDE_PATHS = { "/**" };

	private static final String[] EXCLUDE_PATHS = { "/assets/**", "/error"};

//	인터셉터
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		세션 관리 인터셉터
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns(INCLUDE_PATHS)
		.excludePathPatterns(EXCLUDE_PATHS);
		
//		권한 관리 인터셉터
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns(INCLUDE_PATHS)
				.excludePathPatterns(EXCLUDE_PATHS);
	}

//	ViewResolver 설정
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("classpath:templates/");
		resolver.setSuffix(".html");
		return resolver;
	}
	
	/**
	 * 히든 메소드 필터 활성화
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled", matchIfMissing = true)
	public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new OrderedHiddenHttpMethodFilter(); // 1
	}

//  resources 배치 파일 viewResolve 제외
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

}
