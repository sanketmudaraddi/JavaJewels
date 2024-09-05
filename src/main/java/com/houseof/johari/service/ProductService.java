package com.houseof.johari.service;

import com.houseof.johari.model.Product;
import com.houseof.johari.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Product update(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public boolean delete(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
