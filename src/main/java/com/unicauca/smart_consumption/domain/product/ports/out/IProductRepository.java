package com.unicauca.smart_consumption.domain.product.ports.out;

import java.util.List;
import java.util.Optional;

import com.unicauca.smart_consumption.domain.product.Product;

/**
 * Interface that defines CRUD operations for the {@link Product} entity.
 */
public interface IProductRepository {
    /**
   * Finds a product in the system by its ID.
   *
   * @param id The ID of the {@link Product} to be retrieved.
   * @return An {@link Optional} containing the found {@link Product}, if it exists.
   */
  Optional<Product> findProductById(String id);

  /**
   * Retrieves a list of all products in the system.
   *
   * @return A list of all {@link Product} entities.
   */
  List<Product> findAllProducts();

  /**
   * Retrieves a list of all products in the system.
   *
   * @return A list of all {@link Product} entities.
   */
  List<Product> findAllByIdIn(List<String> ids);

}
