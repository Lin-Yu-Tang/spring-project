package com.tom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import java.net.URI;

@Configuration
public class GatewayMvcConfig {

	@Bean
	public RouterFunction<ServerResponse> getRoute() {
		return route()
				.GET("/get",request -> ServerResponse
						.permanentRedirect(
								request.uriBuilder()
										.scheme("http")
										.host("example.org")
								.build())
						.build())
				.GET("/service1", request -> {
                    URI redirectUri = URI.create("http://localhost:8081" + request.uri().getPath());
                    return ServerResponse.permanentRedirect(redirectUri).build();
                })
                .GET("/service2", request -> {
                    URI redirectUri = URI.create("http://localhost:8082" + request.uri().getPath());
                    return ServerResponse.permanentRedirect(redirectUri).build();
                })
				.build();
	}

}
