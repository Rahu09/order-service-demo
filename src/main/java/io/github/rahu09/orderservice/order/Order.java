package io.github.rahu09.orderservice.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record Order(UUID id, String customerId, List<OrderItem> items, OrderStatus status, Instant createdAt) {

	public Order {
		Objects.requireNonNull(id, "id");
		Objects.requireNonNull(customerId, "customerId");
		Objects.requireNonNull(status, "status");
		Objects.requireNonNull(createdAt, "createdAt");
		items = List.copyOf(items);
	}

	public BigDecimal total() {
		return items.stream().map(OrderItem::lineTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Order cancel() {
		if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
			throw new OrderStateException("Order %s cannot be cancelled in status %s".formatted(id, status));
		}
		return new Order(id, customerId, items, OrderStatus.CANCELLED, createdAt);
	}
}
