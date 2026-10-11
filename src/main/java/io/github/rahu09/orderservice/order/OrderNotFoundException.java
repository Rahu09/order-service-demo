package io.github.rahu09.orderservice.order;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(UUID id) {
		super("Order %s not found".formatted(id));
	}
}
