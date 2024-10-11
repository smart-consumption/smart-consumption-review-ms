package com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.out.IProductRepository;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.CategoryEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.DetailEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.SustainabilityCriteriaEmbeddable;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductJpaEntityMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements IProductRepository{

    private final ProductJpaRepository productJpaRepository;
    private final ProductJpaEntityMapper productPostgresMapper;


    @Override
    public Product createProduct(Product product) {
        ProductJpaEntity entity = productPostgresMapper.toTarget(product);
        return productPostgresMapper.toDomain(productJpaRepository.save(entity));
    }

    @Override
    public Product updateProduct(String id, Product product) {
        ProductJpaEntity productJpaEntity = productJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

        CategoryEmbeddable category = new CategoryEmbeddable();
        category.setCategoryName(product.getCategory().getCategoryName());

        DetailEmbeddable detail = new DetailEmbeddable();
        detail.setDescription(product.getDetail().getDescription());
        detail.setSpecifications(product.getDetail().getSpecifications());

        SustainabilityCriteriaEmbeddable sustainabilityCriteria = new SustainabilityCriteriaEmbeddable();
        sustainabilityCriteria.setCarbonFootprint(product.getSustainabilityCriteria().getCarbonFootprint());
        sustainabilityCriteria.setEnergyEfficiency(product.getSustainabilityCriteria().getEnergyEfficiency());
        sustainabilityCriteria.setResourceUsage(product.getSustainabilityCriteria().getResourceUsage());
        sustainabilityCriteria.setWasteManagement(product.getSustainabilityCriteria().getWasteManagement());
        sustainabilityCriteria.setSustainabilityScore(product.getSustainabilityCriteria().getSustainabilityScore());

        productJpaEntity.setName(product.getName());
        productJpaEntity.setCategory(category);
        productJpaEntity.setDetail(detail);
        productJpaEntity.setSustainabilityCriteria(sustainabilityCriteria);
        productJpaEntity.setStatus(product.getStatus());
        productJpaEntity.setPrice(product.getPrice());

        ProductJpaEntity updatedEntity = productJpaRepository.save(productJpaEntity);

        return productPostgresMapper.toDomain(updatedEntity);
    }

    @Override
    public void deleteProduct(String id) {
        if (productJpaRepository.existsById(id)) {
            productJpaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product not found with id " + id);
        }
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return productJpaRepository.findById(id)
            .map(productPostgresMapper::toDomain);
    }

    @Override
    public List<Product> findAllProducts() {
        return productJpaRepository.findAll().stream()
            .map(productPostgresMapper::toDomain)
            .toList();
    }

    @Override
    public List<Product> findAllByIdIn(List<String> ids) {
        return this.productJpaRepository.findAllById(ids).
        stream().map(productPostgresMapper::toDomain).toList();
    }
    
}
