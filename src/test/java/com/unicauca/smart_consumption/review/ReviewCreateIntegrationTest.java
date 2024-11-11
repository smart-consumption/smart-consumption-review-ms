package com.unicauca.smart_consumption.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.smart_consumption.domain.product.ProductStatus;
import com.unicauca.smart_consumption.domain.product.Rating;
import com.unicauca.smart_consumption.domain.review.ports.out.IReviewRepository;
import com.unicauca.smart_consumption.infrastructure.modules.city.dataproviders.jpa.CityJPAEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.CategoryEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.DetailEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.SustainabilityCriteriaEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.jpa.ProductJpaEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.jpa.ProductJpaRepository;
import com.unicauca.smart_consumption.infrastructure.modules.user.dataproviders.jpa.UserJPAEntity;
import com.unicauca.smart_consumption.infrastructure.modules.user.dataproviders.jpa.UserJPARepository;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ReviewDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReviewCreateIntegrationTest {
    
    @MockBean
    private IReviewRepository reviewRepository;

    @Autowired
    private ProductJpaRepository productRepository;

    @Autowired
    private  UserJPARepository userJPARepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductJpaEntity initialProductEntity;

    private  UserJPAEntity initialUserEntity;

    private String ENDPOINT = "/review";

    private String toJson(ReviewDto data) throws Exception {
        return objectMapper.writeValueAsString(data);
    }

    @BeforeEach
    public void setUp() {
        this.productRepository.deleteAll();
        ProductJpaEntity product = new ProductJpaEntity();

        CategoryEmbeddable categoria = new CategoryEmbeddable();
        categoria.setCategoryName("Category 1");

        DetailEmbeddable detail = new DetailEmbeddable();
        detail.setDescription("Description 1");
        detail.setSpecifications("Specifications 1");

        SustainabilityCriteriaEmbeddable sustainabilityCriteria = new SustainabilityCriteriaEmbeddable();
        sustainabilityCriteria.setCarbonFootprint(8);
        sustainabilityCriteria.setEnergyEfficiency(7);
        sustainabilityCriteria.setResourceUsage(6);
        sustainabilityCriteria.setWasteManagement(9);
        sustainabilityCriteria.setSustainabilityScore(8);
        
        product.setName("Product 1");
        product.setCategory(categoria);
        product.setDetail(detail);
        product.setSustainabilityCriteria(sustainabilityCriteria);
        product.setStatus(ProductStatus.AVAILABLE);

        this.initialProductEntity = this.productRepository.save(product);

        this.userJPARepository.deleteAll();

        CityJPAEntity city = new CityJPAEntity();
        city.setName("city");
        city.setDepartment("departament");


        UserJPAEntity user = new UserJPAEntity();
        user.setName("user");
        user.setUsername("username");
        user.setCity(city);

        this.initialUserEntity = this.userJPARepository.save(user);


    }

    @Test
    public void testCreateReview() throws Exception {
        ReviewDto review = new ReviewDto();
        review.setRating(Rating.TERRIBLE);
        review.setComment("This is a terrible product");
        review.setDate(LocalDateTime.now());

        this.mockMvc.perform(post(ENDPOINT+"/{userId}/{productId}", this.initialUserEntity.getId(), this.initialProductEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(review)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateReviewEmptyComment() throws Exception {
        ReviewDto review = new ReviewDto();
        review.setRating(Rating.TERRIBLE);
        review.setComment("");
        review.setDate(LocalDateTime.now());

        this.mockMvc.perform(post(ENDPOINT+"/{userId}/{productId}", this.initialUserEntity.getId(), this.initialProductEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(review)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewEmptyRating() throws Exception {
        ReviewDto review = new ReviewDto();
        review.setRating(null);
        review.setComment("This is a terrible product");
        review.setDate(LocalDateTime.now());

        this.mockMvc.perform(post(ENDPOINT+"/{userId}/{productId}", this.initialUserEntity.getId(), this.initialProductEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(review)))
                .andExpect(status().isBadRequest());
    }

}
