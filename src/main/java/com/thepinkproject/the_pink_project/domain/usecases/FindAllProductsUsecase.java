package com.thepinkproject.the_pink_project.domain.usecases;

import com.thepinkproject.the_pink_project.domain.models.Product;
import com.thepinkproject.the_pink_project.domain.repository.jpaRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProductsUsecase {
    ProductRepository productRepository;

    @Autowired
    public FindAllProductsUsecase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> call() {
        return this.productRepository.findAll();
    }
}
