package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ProductDto;

import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link ProductDto} and {@link Product}.
 * <p>The implementation of this interface is automatically generated by Map Struct during the build process, ensuring
 * that the mappings are efficient and error-free.</p>
 *
 * @see ProductDto
 * @see Product
 * @see EntityMapper
 */
@Mapper(componentModel = "spring")
public interface ProductMapper
    extends EntityMapper<ProductDto, Product>{
}
