package ec.edu.uce.practica.dto;

import ec.edu.uce.practica.jpa.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {

    private Long id;
    private String nombre;
    private List<Long> productsId;

    public CategoryDTO(){}

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.nombre = category.getNombre();
    }

    public static CategoryDTO toDTO(Category category){
        if(category == null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO(category);
        categoryDTO.setId(category.getId());
        categoryDTO.setNombre(category.getNombre());
        categoryDTO.setProductsId(category.getProduct().stream().map(product->
                product.getId()).collect(Collectors.toList()));
        return categoryDTO;
    }

    public static Category toEntity(CategoryDTO categoryDTO){
        if(categoryDTO == null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setNombre(categoryDTO.getNombre());
        return category;
    }

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
