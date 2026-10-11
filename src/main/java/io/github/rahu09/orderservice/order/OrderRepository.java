package io.github.rahu09.orderservice.order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

	Order save(Order order);

	Optional<Order> findById(UUID id);
}
