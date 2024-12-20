package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Detail;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.CategoryEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.DetailEmbeddable;
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

    default CategoryEmbeddable map(Category category) {
        if (category == null) {
            return null;
        }
        CategoryEmbeddable categoryEmbeddable = new CategoryEmbeddable();
        categoryEmbeddable.setCategoryName(category.getCategoryName());
        return categoryEmbeddable;
    }

    default Category map(CategoryEmbeddable categoryEmbeddable) {
        if (categoryEmbeddable == null) {
            return null;
        }
        Category category = new Category(categoryEmbeddable.getCategoryName()); 
        return category;
    }

    default DetailEmbeddable map(Detail detail) {
        if (detail == null) {
            return null;
        }
        DetailEmbeddable detailEmbeddable = new DetailEmbeddable();
        detailEmbeddable.setDescription(detail.getDescription());
        detailEmbeddable.setSpecifications(detail.getSpecifications());
        return detailEmbeddable;
    }

    default Detail map(DetailEmbeddable detailEmbeddable) {
        if (detailEmbeddable == null) {
            return null;
        }
        Detail detail = new Detail(detailEmbeddable.getDescription(), detailEmbeddable.getSpecifications());
        return detail;
    }


    /**
     * Maps a domain entity to a DTO (Data Transfer Object).
     *
     * @param entity The domain entity to be mapped to the DTO.
     * @return The DTO object.
     */

    ProductJpaEntity toTarget(Product entity);
}
