package com.cho.hello;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/*
	SpringBootServletInitializer 클래스
	Boot 프로젝트에서 root-context.xml, servlet-context.xml 을 대신하는 클래스
	이 클래스에서 HelloApplication.class.main() method 를 내부에서 호출한다.
 */

/*
	@ComponentScan
 Annotation
 Class 를 scan 하여 bean 으로 생성하게 하는 지시어
 */
@ComponentScan({"com.cho.hello.controller", "com.cho.hello.service"})
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloApplication.class);
	}

}
