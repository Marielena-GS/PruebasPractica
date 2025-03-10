package ec.edu.uce.practica.repository;

import ec.edu.uce.practica.jpa.Category;
import ec.edu.uce.practica.jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}
