package com.thepinkproject.the_pink_project.domain.usecases;

import com.thepinkproject.the_pink_project.domain.dtos.ChangeStatusDTO;
import com.thepinkproject.the_pink_project.domain.models.Product;
import com.thepinkproject.the_pink_project.domain.repository.jpaRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductStatusUsecase {
    private final ProductRepository productRepository;

    @Autowired
    public UpdateProductStatusUsecase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void call(ChangeStatusDTO statusDTO) {
        Optional<Product> optionalProduct = this.productRepository.findById(statusDTO.getId());

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setAvailability(statusDTO.status);

            this.productRepository.save(product);

            return;
        }

        System.out.println("Product wast saved");

    }

}
