package ec.edu.uce.practica.controller;

import ec.edu.uce.practica.Service.CategoryService;
import ec.edu.uce.practica.dto.CategoryDTO;
import ec.edu.uce.practica.jpa.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listarCategoria() {
        return ResponseEntity.ok(categoryService.ListarCategoria());
    }

    @GetMapping("/nombre/{nombre}")
    public List<CategoryDTO> ListarCategoriaPorNombre(@PathVariable String nombre){
       return categoryService.ListarCategoriaPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public CategoryDTO ListarCategoriaPorId(@PathVariable Long id) {
        return categoryService.ListarCategoriaPorId(id);
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO category){
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO category){
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
