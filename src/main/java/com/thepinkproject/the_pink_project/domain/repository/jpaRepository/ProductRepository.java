package com.thepinkproject.the_pink_project.domain.repository.jpaRepository;

import com.thepinkproject.the_pink_project.domain.enums.Status;
import com.thepinkproject.the_pink_project.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByName(String name);
    List<Product> findByAvailability(Status available);

}
