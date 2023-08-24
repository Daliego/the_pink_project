package com.thepinkproject.the_pink_project.domain.usecases;

import com.thepinkproject.the_pink_project.domain.dtos.ChangeStatusDTO;
import com.thepinkproject.the_pink_project.domain.models.Product;
import com.thepinkproject.the_pink_project.domain.repository.jpaRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductUsecase {
    private final ProductRepository productRepository;

    @Autowired
    public DeleteProductUsecase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void call(String id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        System.out.println(id);
        System.out.println(optionalProduct.isPresent());

        if (optionalProduct.isPresent()) {
            System.out.println("Agora vai deleter");
            Product product = optionalProduct.get();

            this.productRepository.delete(product);
        }

        System.out.println("Product wast deleted");
    }
}
