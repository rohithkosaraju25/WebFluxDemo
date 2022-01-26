package com.cg.webflux.dao;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.cg.webflux.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	private static void sleepExectuion(int i) throws InterruptedException  {
		Thread.sleep(1000);
	}

	public List<Customer> getCustomer() {
		return IntStream.rangeClosed(1, 10)
				.peek(value -> {
					try {
						sleepExectuion(value);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				})
				.peek(i-> System.out.println("processing count : "+i))
				.mapToObj(i -> new Customer(i, "customer" + i))
				.collect(Collectors.toList());

	}
	
	public Flux<Customer> getCustomerStream() {
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i->System.out.println("processing count in stream " + i))
				.map(i-> new Customer(i, "customer" + i));

	}
}
