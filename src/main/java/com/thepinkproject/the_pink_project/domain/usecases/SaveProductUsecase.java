package com.thepinkproject.the_pink_project.domain.usecases;

import com.thepinkproject.the_pink_project.domain.dtos.ProductDTO;
import com.thepinkproject.the_pink_project.domain.models.Product;
import com.thepinkproject.the_pink_project.domain.repository.jpaRepository.ProductRepository;
import com.thepinkproject.the_pink_project.helperClass.HelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class SaveProductUsecase {
    private final ProductRepository repository;

    @Autowired
    public SaveProductUsecase(ProductRepository repository) {
        this.repository = repository;
    }

    public void call(ProductDTO product) {
        Product newProduct = new Product();

        newProduct.setId(UUID.randomUUID().toString().replace("-", ""));
        newProduct.setName(product.getName());
        newProduct.setAvailability(product.getAvailability());
        newProduct.setDestination(product.getDestination());
        newProduct.setRentabilityTax(product.getRentabilityTax());
        newProduct.setMinimumTime(product.getMinimunTime());
        newProduct.setAdministrationTax(product.getAdministrationTax());
        newProduct.setVencimento(LocalDate.now().plusMonths(product.getMinimunTime()));


        this.repository.save(newProduct);
    }

}
