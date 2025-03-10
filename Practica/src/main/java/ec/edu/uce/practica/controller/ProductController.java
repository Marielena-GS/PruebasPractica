package ec.edu.uce.practica.controller;

import ec.edu.uce.practica.Service.ProductService;
import ec.edu.uce.practica.dto.ProductDTO;
import ec.edu.uce.practica.jpa.Category;
import ec.edu.uce.practica.jpa.Product;
import ec.edu.uce.practica.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    };

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listarProductos() {
        return ResponseEntity.ok(productService.listarProductos());
    }

    @GetMapping("/nombre/{nombre}")
    public List<ProductDTO> listarProductoPorNombre(@PathVariable String nombre){
        return productService.listarProductoPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public ProductDTO listarProductoPorID(@PathVariable Long id){
        return productService.listarProductoPorID(id);
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> listarProductoPorCategoria(@PathVariable Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoría no encontrada"));
        return category.getProduct().stream().map(ProductDTO::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ProductDTO crearProducto(@RequestBody ProductDTO product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
