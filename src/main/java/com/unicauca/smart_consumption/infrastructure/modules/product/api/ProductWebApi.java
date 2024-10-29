package com.unicauca.smart_consumption.infrastructure.modules.product.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.in.IProductService;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ProductDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Product APIs", description = "Product web APIs for command services")
public class ProductWebApi {

  private final IProductService productCommandService;
  private final ProductMapper productMapper;


  @GetMapping("/{entityId}")
  public ResponseEntity<ResponseDto<ProductDto>> getProductById(@PathVariable String entityId) {
    ResponseDto<Product> productResponse = productCommandService.findProductById(entityId);
    ProductDto productMongoDto = productMapper.toTarget(productResponse.getData());
    ResponseDto<ProductDto> productDtoResponse = new ResponseDto<>(productResponse.getStatus(), productResponse.getMessage(), productMongoDto);
    return productDtoResponse.of();
  }

  @GetMapping
  public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts() {
    ResponseDto<List<Product>> productResponse = productCommandService.findAllProducts();
    return new ResponseDto<>(
        productResponse.getStatus(),
        productResponse.getMessage(),
        productResponse.getData().stream()
            .map(productMapper::toTarget)
            .toList()
    ).of();
  }

}
