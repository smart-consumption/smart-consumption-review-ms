package com.unicauca.smart_consumption.application.service.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.in.IProductService;
import com.unicauca.smart_consumption.domain.product.ports.out.IProductRepository;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductRepository productRepository;

    @Override
    public ResponseDto<Product> findProductById(final String id) {
        Product product = productRepository.findProductById(id)
            .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id)));
        return new ResponseDto<>(HttpStatus.OK.value(),
            MessageLoader.getInstance().getMessage(MessagesConstant.IM001), product);
    }

    @Override
    public ResponseDto<List<Product>> findAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        return new ResponseDto<>(HttpStatus.OK.value(),
            MessageLoader.getInstance().getMessage(MessagesConstant.IM001), products);
    }
        
}
