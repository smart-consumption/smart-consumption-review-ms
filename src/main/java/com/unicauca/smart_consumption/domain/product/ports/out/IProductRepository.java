package com.unicauca.smart_consumption.domain.product.ports.out;

import java.util.List;
import java.util.Optional;

import com.unicauca.smart_consumption.domain.product.Product;

/**
 * Interface that defines CRUD operations for the {@link Product} entity.
 */
public interface IProductRepository {

  /**
   * Creates a new product in the system.
   *
   * @param product The {@link Product} to be created.
   * @return The created {@link Product}.
   */
  Product createProduct(Product product);

  /**
   * Updates an existing product in the system.
   *
   * @param id The ID of the {@link Product} to be updated.
   * @param product The {@link Product} with updated information.
   * @return The updated {@link Product}.
   */
  Product updateProduct(String id, Product product);

  /**
   * Deletes an existing product from the system.
   *
   * @param id The ID of the {@link Product} to be deleted.
   */
  void deleteProduct(String id);

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
