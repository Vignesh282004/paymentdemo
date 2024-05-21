package com.stripe.paymentdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentdemoApplication.class, args);
		System.out.println("Stripe Application Started");
	}

}
