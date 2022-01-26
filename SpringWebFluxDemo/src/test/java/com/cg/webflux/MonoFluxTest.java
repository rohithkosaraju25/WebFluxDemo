package com.cg.webflux;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class MonoFluxTest {
	@Test
	@Disabled
	public void testMono() {
		Mono<?> monoString = Mono.just("mono-testing")
				//.then(Mono.error(new RuntimeException("Exception occurred")))
				.log();
		monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
		
	}
	
	@Test
	public void testFlux() {
		Flux<?> fluxString=Flux.just("spring","springboot","hibernate","microservice")
				.concatWithValues("AWS")
				.concatWith(Flux.error(new RuntimeException("Exception occured")))
				.log();
		fluxString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
		
	}
}
