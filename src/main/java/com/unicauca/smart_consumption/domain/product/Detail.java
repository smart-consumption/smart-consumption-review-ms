package com.unicauca.smart_consumption.domain.product;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Detail {

    private String description;
    private String specifications;

    public Detail() {
    }

    public Detail(String description, String specifications) {
        if (!Objects.nonNull(description) || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description cannot be null or empty.");
        }
        this.description = description;
        this.specifications = specifications;
    }
}
