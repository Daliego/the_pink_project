package com.thepinkproject.the_pink_project.domain.usecases;

import com.thepinkproject.the_pink_project.domain.dtos.ChangeStatusDTO;
import com.thepinkproject.the_pink_project.domain.dtos.ProductDTO;
import com.thepinkproject.the_pink_project.domain.enums.Status;
import com.thepinkproject.the_pink_project.domain.models.Product;
import com.thepinkproject.the_pink_project.helperClass.HelperClass;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.thepinkproject.the_pink_project.domain.repository.jpaRepository.ProductRepository;
import org.springframework.beans.BeanUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class ProductsUsecases {

    @NonNull
    private final ProductRepository productRepository;

    @Schema(title = "CreateProduct", description = "Parameters required to create a product: ProductDto", required = true)
    public Product createProduct(ProductDTO productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        if (HelperClass.isGonnaPassDayPay(productDto.getExpirationDate(), productDto.getMinPeriod())) {
            throw new DateTimeException("minimun period passed the expiration data");
        }

        return this.productRepository.save(product);
    }

    public Product getProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (!product.isPresent()) {
            throw new ObjectNotFoundException(productId, product);
        }

        return product.get();
    }

    public Product updateProduct(ProductDTO productDTO) {
        Optional<Product> product = this.productRepository.findById(productDTO.getId());

        if (!product.isPresent()) {
            throw new ObjectNotFoundException(productDTO.getName(), product);
        }

        if (HelperClass.isGonnaPassDayPay(productDTO.getExpirationDate(), productDTO.getMinPeriod())) {
            throw new DateTimeException("minimun period passed the expiration data");
        }

        BeanUtils.copyProperties(productDTO, product);

        return productRepository.save(product.get());
    }

    // Delete a product by ID
    public void deleteProduct(String productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (!product.isPresent()) {
            throw new ObjectNotFoundException(productId, product);
        }

        productRepository.deleteById(productId);
    }

    public List<Product> getAllProducts() {
        // Implementation using the productRepository
        return productRepository.findAll();
    }

    // Query products by name
    public Optional<Product> getProductsByName(String name) {
        // Implementation using the productRepository
        return productRepository.findByName(name);
    }

    // Filter products by availability
    public List<Product> getProductsByAvailability(Status available) {
        // Implementation using the productRepository
        return productRepository.findByAvailability(available);
    }

    public Product updateProductStatus(ChangeStatusDTO changeStatusDTO) {
        Optional<Product> product = this.productRepository.findById(changeStatusDTO.getId());

        if (!product.isPresent()) {
            throw new ObjectNotFoundException(changeStatusDTO.getId(), product);
        }

        product.get().setAvailability(changeStatusDTO.getStatus());

        return productRepository.save(product.get());
    }

    // Calculate rentability tax for a product
    public double calculateRentabilityTax(ProductDTO productDto) {
        Product product = new Product();

        BeanUtils.copyProperties(productDto, product);
         return product.getRentabilityTax() * product.getMinPeriod();
    }

    public boolean hasDailyLiquidity(ProductDTO productDto) {
        Product product = new Product();

        BeanUtils.copyProperties(productDto, product);

         return product.getDailyLiquidity();
    }

    public Product updateVencimentoDate(String productId, LocalDate newVencimento) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setExpirationDate(newVencimento);
            return productRepository.save(product);
        }

        return null;
    }

    public Product updateDestination(String productId, String newDestination) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setDestination(newDestination);
            return productRepository.save(product);
        }
        return null;
    }

}
