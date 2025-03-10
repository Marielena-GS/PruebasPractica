package ec.edu.uce.practica.Service;

import ec.edu.uce.practica.dto.CategoryDTO;
import ec.edu.uce.practica.jpa.Category;
import ec.edu.uce.practica.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /*
    // Método asíncrono para crear categoría
    @Async
    public void createCategoryAsync(Category category) {
        categoryRepository.save(category);
    }*/

    //segunda forma para manejo de hilos
    public void createCategoryAsync(Category category) {
        ThreadPoolManaged.execute(()->categoryRepository.save(category));
    }

    public List<CategoryDTO> ListarCategoria() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> ListarCategoriaPorNombre(String nombre) {
        return categoryRepository.findByNombre(nombre).stream()
                .map(CategoryDTO::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO ListarCategoriaPorId(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryDTO::toDTO)
                .orElseThrow(()-> new RuntimeException("Categoría no encontrada"));
    }
    @Transactional
    public CategoryDTO createCategory(CategoryDTO category) {
        Category category1 = CategoryDTO.toEntity(category);
        return CategoryDTO.toDTO(categoryRepository.save(category1));
    }

    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO category) {
        Category categoryexiste = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Categoria no encontrada"));
        categoryexiste.setNombre(category.getNombre());
        return CategoryDTO.toDTO(categoryRepository.save(categoryexiste));
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
