package ec.edu.uce.practica.dto;
import ec.edu.uce.practica.jpa.Product;

public class ProductDTO {

    private Long id;
    private String name;
    private double price;
    private Long categoryID;
    private String categoryName; //nombre de la categoría

    public ProductDTO() {}

    public static ProductDTO toDTO(Product product) {
        if(product == null){
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        if(product.getCategory() != null){
            dto.setCategoryID(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getNombre());
        }
        return dto;
    }

    public static Product toEntity(ProductDTO productDTO){
        if(productDTO == null){
            return null;
        }
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
