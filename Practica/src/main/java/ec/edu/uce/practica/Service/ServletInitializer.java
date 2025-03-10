package ec.edu.uce.practica.Service;

import ec.edu.uce.practica.dto.CategoryDTO;
import ec.edu.uce.practica.dto.ProductDTO;
import ec.edu.uce.practica.jpa.Category;
import ec.edu.uce.practica.jpa.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServletInitializer implements CommandLineRunner {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;


	@Override
	public void run(String... args) throws Exception {
		CategoryDTO category1 = new CategoryDTO();
		category1.setNombre("Enlatados");
		category1 = categoryService.createCategory(category1);

		ProductDTO product1 = new ProductDTO();
		product1.setPrice(2.0);
		product1.setName("Jamon");
		product1.setCategoryID(category1.getId());

		CategoryDTO category2 = new CategoryDTO();
		category2.setNombre("Electronicos");
		category2 = categoryService.createCategory(category2);

		ProductDTO product2 = new ProductDTO();
		product2.setPrice(40.5);
		product2.setName("Aire Acondicionado");
		product2.setCategoryID(category2.getId());

		productService.createProduct(product1);
		productService.createProduct(product2);

	}
	//docker run -d --name postgres -e POSTGRES_USER=postgre -e POSTGRES_PASSWORD=postgre -e POSTGRES_DB=practica -p 5432:5432 -v postgresdata:/var/lib/postgresql/data postgres:latest
	//docker run -d --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=labcom,2015 -e POSTGRES_DB=postgres -p 5432:5432 -v postgresdata:/var/lib/postgresql/data postgres:latest

}
