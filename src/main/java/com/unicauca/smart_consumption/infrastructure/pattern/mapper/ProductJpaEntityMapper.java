package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.jpa.ProductJpaEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Mapper interface for converting between {@link ProductJpaEntity} and {@link Product}.
 * <p>The implementation of this interface is automatically generated by Map Struct during the build process, ensuring
 * that the mappings are efficient and error-free.</p>
 *
 * @see ProductJpaEntity
 * @see Product
 * @see EntityMapper
 */
@Mapper(componentModel = "spring")
public interface ProductJpaEntityMapper
    extends EntityMapper<ProductJpaEntity, Product> {
    /**
     * Maps a Dto (Data Transfer Object) to a domain entity.
     *
     * @param dto The Dto object to be mapped to the domain entity.
     * @return The domain entity.
     */
    @Mapping(target = "reviews", ignore = true)
    Product toDomain(ProductJpaEntity dto);

    /**
     * Maps a domain entity to a DTO (Data Transfer Object).
     *
     * @param entity The domain entity to be mapped to the DTO.
     * @return The DTO object.
     */

    ProductJpaEntity toTarget(Product entity);
}