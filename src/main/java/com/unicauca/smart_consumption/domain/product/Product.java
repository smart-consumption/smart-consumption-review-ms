package com.unicauca.smart_consumption.domain.product;

import com.unicauca.smart_consumption.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
  private String id;
  private String name;
  private Category category;
  private Detail detail;
  private SustainabilityCriteria sustainabilityCriteria;
  private ProductStatus status;
  private double price;
  private List<Review> reviews;

  public void updateProduct(String name, Category category, Detail detail,
                            SustainabilityCriteria sustainabilityCriteria, ProductStatus status, double price) {
    if (name != null && !name.trim().isEmpty()) {
      this.name = name;
    }
    if (category != null) {
      this.category = category;
    }
    if (detail != null) {
      this.detail = detail;
    }
    if (sustainabilityCriteria != null) {
      this.sustainabilityCriteria = sustainabilityCriteria;
    }
    if (status != null) {
      this.status = status;
    }
    if (price > 0) {
      this.price = price;
    }
  }

  public void applyDiscount(double percentage) {
    if (percentage > 0 && percentage <= 100) {
      this.price -= this.price * (percentage / 100);
    }
  }

  public boolean isSustainable() {
    return this.sustainabilityCriteria.getSustainabilityScore() > 75;
  }

  public boolean isInCategory(Category category) {
    return this.category.equals(category);
  }

}
