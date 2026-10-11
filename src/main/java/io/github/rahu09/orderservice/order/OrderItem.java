package io.github.rahu09.orderservice.order;

import java.math.BigDecimal;
import java.util.Objects;

public record OrderItem(String sku, int quantity, BigDecimal unitPrice) {

	public OrderItem {
		Objects.requireNonNull(sku, "sku");
		Objects.requireNonNull(unitPrice, "unitPrice");
		if (quantity < 1) {
			throw new IllegalArgumentException("quantity must be >= 1");
		}
	}

	public BigDecimal lineTotal() {
		return unitPrice.multiply(BigDecimal.valueOf(quantity));
	}
}
