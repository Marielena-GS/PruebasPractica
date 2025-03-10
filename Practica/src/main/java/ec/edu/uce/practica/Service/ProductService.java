package ec.edu.uce.practica.Service;

import ec.edu.uce.practica.dto.ProductDTO;
import ec.edu.uce.practica.jpa.Category;
import ec.edu.uce.practica.jpa.Product;
import ec.edu.uce.practica.repository.CategoryRepository;
import ec.edu.uce.practica.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    /*
    // Método asíncrono para crear categoría
    @Async
    public void createProductAsync(Product poduct) {
        productRepository.save(product);
    }*/

    //Para manejo de hilos
    public void createProductAsync(Product product) {
        ThreadPoolManaged.execute(()->productRepository.save(product));
    }

    public List<ProductDTO> listarProductos(){
        return productRepository.findAll().stream()
                .map(ProductDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> listarProductoPorNombre(String nombre){
        return productRepository.findByName(nombre)
                .stream().map(ProductDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO listarProductoPorID(Long id){
        return productRepository.findById(id)
                .map(ProductDTO::toDTO)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
    }

    public List<ProductDTO> listarProductoPorCategoria(Category categoria){
        return productRepository.findByCategory(categoria)
                .stream().map(ProductDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO){
       Product product = ProductDTO.toEntity(productDTO);
       if(productDTO.getCategoryID()!= null){
           Category category = categoryRepository.findById(productDTO.getCategoryID())
                   .orElseThrow(()-> new RuntimeException("Categoria no Encontrada"));
           product.setCategory(category);
           if(!category.getProduct().contains(product)){
               category.getProduct().add(product);
           }
       }
       return ProductDTO.toDTO(productRepository.save(product));
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product productexiste = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        productexiste.setName(productDTO.getName());
        productexiste.setPrice(productDTO.getPrice());
        if(productDTO.getCategoryID() != null){
            Category category = categoryRepository.findById(productDTO.getCategoryID())
                    .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
            productexiste.setCategory(category);
        }
        return ProductDTO.toDTO(productRepository.save(productexiste));
    }

    @Transactional
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
