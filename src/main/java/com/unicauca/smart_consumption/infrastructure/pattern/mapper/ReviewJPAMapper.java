package com.unicauca.smart_consumption.infrastructure.pattern.mapper;


import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Detail;
import com.unicauca.smart_consumption.domain.review.Review;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.CategoryEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.DetailEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.review.dataproviders.jpa.ReviewJPAEntity;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link ReviewJPAEntity} and {@link Review}.
 * <p>The implementation of this interface is automatically generated by Map Struct during the build process, ensuring
 * that the mappings are efficient and error-free.</p>
 *
 * @see ReviewJPAEntity
 * @see Review
 * @see EntityMapper
 */

@Mapper(componentModel = "spring")
public interface ReviewJPAMapper extends EntityMapper<ReviewJPAEntity, Review>{

    default Category map(CategoryEmbeddable categoryEmbeddable) {
        if (categoryEmbeddable == null) {
            return null;
        }
        Category category = new Category(categoryEmbeddable.getCategoryName());
        return category;
    }

    default Detail map(DetailEmbeddable detailEmbeddable) {
        if (detailEmbeddable == null) {
            return null;
        }
        Detail detail = new Detail(detailEmbeddable.getDescription(), detailEmbeddable.getSpecifications());
        return detail;
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
}

