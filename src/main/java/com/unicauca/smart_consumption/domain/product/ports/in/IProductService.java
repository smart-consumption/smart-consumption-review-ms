package com.unicauca.smart_consumption.domain.product.ports.in;

import java.util.List;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.product.Product;

/**
 * Interface that defines CRUD operations for the {@link Product} entity.
 */
public interface IProductService {
    /**
   * Finds a product in the system by its ID.
   *
   * @param id The ID of the {@link Product} to be retrieved.
   * @return A {@link ResponseDto} containing the found product if it exists, and an HTTP status code.
   */
  ResponseDto<Product> findProductById(String id);

  /**
   * Retrieves a list of all products in the system.
   *
   * @return A {@link ResponseDto} containing the list of all products and an HTTP status code.
   */
  ResponseDto<List<Product>> findAllProducts();
}
