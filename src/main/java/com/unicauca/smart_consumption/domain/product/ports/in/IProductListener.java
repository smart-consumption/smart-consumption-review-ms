package com.unicauca.smart_consumption.domain.product.ports.in;

import com.unicauca.smart_consumption.domain.product.Product;

public interface IProductListener {
    void receiveCreatedProduct(Product product);
}
