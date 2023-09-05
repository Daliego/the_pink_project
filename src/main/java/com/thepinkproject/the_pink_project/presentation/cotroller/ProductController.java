package com.thepinkproject.the_pink_project.presentation.cotroller;

import com.thepinkproject.the_pink_project.domain.dtos.ProductDTO;
import com.thepinkproject.the_pink_project.domain.dtos.ChangeStatusDTO;
import com.thepinkproject.the_pink_project.domain.enums.Status;
import com.thepinkproject.the_pink_project.domain.models.Product;
import com.thepinkproject.the_pink_project.domain.repository.jpaRepository.ProductRepository;
import com.thepinkproject.the_pink_project.domain.usecases.ProductsUsecases;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Tag(name = "Products", description = "Manage user products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    @NonNull
    final ProductRepository productRepository;

    @NonNull
    final ProductsUsecases productsUsecases;

    @Operation(summary = "Get all Products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
    })
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productsUsecases.getAllProducts();

        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Get Product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
    })
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable String id) {
        try {
            Product product = productsUsecases.getProductById(id);

            return ResponseEntity.ok().body(product);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Create a new Product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
            }),
    })
    @PostMapping("/product")
    public ResponseEntity<Object> createPost(@RequestBody ProductDTO productDTO) throws Exception {
        try {
            Product product = productsUsecases.createProduct(productDTO);

            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Change Product Status using the id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
    })
    @PutMapping("/product/{id}/{status}")
    public ResponseEntity<Object> changeStatus(@PathVariable String id, @PathVariable Status status){
        try {
            ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
            changeStatusDTO.setId(id);
            changeStatusDTO.setStatus(status);

            Product updatedProduct = productsUsecases.updateProductStatus(changeStatusDTO);

            return ResponseEntity.ok().body(updatedProduct);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Update Product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
    })
    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateInvestment(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        try {
            productDTO.setId(id);

            Product product = productsUsecases.updateProduct(productDTO);

            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")
            }),
    })
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteInvestment(@PathVariable("id") String id) {
        try {
            productsUsecases.deleteProduct(id);

            return ResponseEntity.ok().body("the product %s were deleted".formatted(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

//    @GetMapping("/updateProduct/{id}")
//    public String updateProduct(@PathVariable("id") String id, ModelMap model) {
//        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
//
//        model.addAttribute("changeStatusDto", changeStatusDTO);
//        model.addAttribute("Id", id);
//
//        return "updateFile";
//    }


//    @GetMapping("/investimentsPresentation")
//    public String getAllProduct(ModelMap model) {
//
//        FindAllProductsUsecase findAllProductsUsecase = new FindAllProductsUsecase(productRepository);
//        final List<Product> allProductDtos = findAllProductsUsecase.call();
//
//        model.addAttribute("investmentsArray", allProductDtos);
//
//        return "productsView";
//    }

}