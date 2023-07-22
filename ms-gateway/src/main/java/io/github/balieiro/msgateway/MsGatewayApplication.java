package io.github.balieiro.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route("ms-clientes", r -> r
						.path("/clientes")
						.uri("http://localhost:8082"))
				.route("ms-cartoes", r -> r
						.path("/cartoes")
						.uri("http://localhost:8083"))
				.build();
	}
}
