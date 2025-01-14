package com.ratan.react.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ratan.react.Model.Product;
import com.ratan.react.repository.ProductRepository;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="*")

public class ProductController 
{
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product product)
	{
		Product saveProduct=productRepository.save(product);
		
		return ResponseEntity.status(HttpStatus.CREATED)
		                     .header("save","saved successfully")
		                     .body("save");
		
		
		
	}
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
	    List<Product> products = productRepository.findAll();
	    return ResponseEntity.ok(products);
	}
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productRepository.existsById(id);
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully.");

}
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> editProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
	    return productRepository.findById(id).map(existingProduct -> {
	        existingProduct.setName(updatedProduct.getName());
	        existingProduct.setPrice(updatedProduct.getPrice());
	        // Add more fields if needed

	        productRepository.save(existingProduct);
	        return ResponseEntity.ok("Product updated successfully.");
	    }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found."));
	}

}

