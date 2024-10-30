package com.unicauca.smart_consumption.infrastructure.modules.review.dataproviders.jpa;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJPARepository  extends JpaRepository<ReviewJPAEntity, String> {

    List<ReviewJPAEntity> findReviewsByProductId(String productId);
//    List<ReviewJPAEntity> findByProduct(ProductJPAEntity product);
//    List<ReviewJPAEntity> findByUser(UserJPAEntity user);
//    List<ReviewJPAEntity> findReviewsWithMinimumRating(int minRating);
}
