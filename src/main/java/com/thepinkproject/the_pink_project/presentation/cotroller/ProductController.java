package com.thepinkproject.the_pink_project.presentation.cotroller;

import com.thepinkproject.the_pink_project.domain.dtos.ProductDTO;
import com.thepinkproject.the_pink_project.domain.dtos.ChangeStatusDTO;
import com.thepinkproject.the_pink_project.domain.models.Product;
import com.thepinkproject.the_pink_project.domain.repository.jpaRepository.ProductRepository;
import com.thepinkproject.the_pink_project.domain.usecases.DeleteProductUsecase;
import com.thepinkproject.the_pink_project.domain.usecases.FindAllProductsUsecase;
import com.thepinkproject.the_pink_project.domain.usecases.SaveProductUsecase;
import com.thepinkproject.the_pink_project.domain.usecases.UpdateProductStatusUsecase;
import com.thepinkproject.the_pink_project.helperClass.HelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    final
    ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public String getPost(ModelMap model) {
        ProductDTO productDTO = new ProductDTO();
        HelperClass helper = new HelperClass();

        model.addAttribute("productDTO", productDTO);
        model.addAttribute("helper", helper);

        return "formFile";
    }

    @PostMapping("/product")
    public String createPostFromForm(@ModelAttribute ProductDTO productDTO) {
        HelperClass helper = new HelperClass();

//        if (helper.isGonnaPassDayPay(productDTO.getVencimento(), productDTO.getMinimunTime())) {
//            return "redirect: /product?text=Investment%20Creation%20Error";
//        } else {
            SaveProductUsecase saveUser = new SaveProductUsecase(productRepository);

            saveUser.call(productDTO);

            return "redirect:/investimentsPresentation";
//        }
    }

    @PostMapping("/updateProduct/{id}")
    public String updateInvestment(@PathVariable("id") String id, @ModelAttribute ChangeStatusDTO changeStatusDTO) {
        changeStatusDTO.setId(id);

        UpdateProductStatusUsecase updateProductStatusUsecase = new UpdateProductStatusUsecase(productRepository);
        updateProductStatusUsecase.call(changeStatusDTO);

        return "redirect:/investimentsPresentation";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteInvestment(@PathVariable("id") String id) {
        System.out.println("Chegou aqui");
        DeleteProductUsecase deleteProductUsecase = new DeleteProductUsecase(productRepository);

        deleteProductUsecase.call(id);

        return "redirect:/investimentsPresentation";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") String id, ModelMap model) {
        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();

        model.addAttribute("changeStatusDto", changeStatusDTO);
        model.addAttribute("Id", id);

        return "updateFile";
    }


    @GetMapping("/investimentsPresentation")
    public String getAllProduct(ModelMap model) {

        FindAllProductsUsecase findAllProductsUsecase = new FindAllProductsUsecase(productRepository);
        final List<Product> allProducts = findAllProductsUsecase.call();

        model.addAttribute("investmentsArray", allProducts);

        return "productsView";
    }

}