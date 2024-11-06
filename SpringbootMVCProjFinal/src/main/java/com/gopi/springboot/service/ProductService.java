package com.gopi.springboot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gopi.springboot.MVC.ProductEntity;
import com.gopi.springboot.model.ProductModel;
import com.gopi.springboot.repository.ProductRepository;

@Service
public class ProductService 
{
	@Autowired
	ProductRepository productRepository;
	
	public void saveProductData(ProductModel productModel)
	{
		//calculate the discount price 
		double price=productModel.getProPrice();
		String category=productModel.getProCategory();
		double dprice=0.0;
		switch(category)
		{
		case "mobile" :
			dprice=price*0.1;
			break;
		case "laptop" :
			dprice=price*0.15;
			break;
		case "printer" :
			dprice=price*0.2;
			break;
		case "camera" :
			dprice=price*0.25;
			break;
		
		}
		
		//read the data from the model and set the data to entity
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProName(productModel.getProName());
		productEntity.setProPrice(productModel.getProPrice());
		productEntity.setProBrand(productModel.getProBrand());
		productEntity.setProDescription(productModel.getProDescription());
        productEntity.setProCategory(productModel.getProCategory());
        
        productEntity.setProDiscount(dprice);
        productEntity.setCreatedAt(LocalDate.now());
        productEntity.setCreatedBy(System.getProperty("user.name"));
        
        productRepository.save(productEntity);
     }
	public List<ProductEntity>getAllProducts()
	{
	List<ProductEntity>products=productRepository.findAll();
	return products;
	
    }
	public void deleteProductById(@RequestParam Long id)
	{
		productRepository.deleteById(id);
	}

	public ProductEntity getProductById(Long proId) {
        return productRepository.findById(proId).orElse(null);
}
	public Optional<ProductEntity> editProductById(@RequestParam long id)
	{
		Optional<ProductEntity> product = productRepository.findById(id);
		return product;
	}

	public Optional<ProductEntity> editProductById(@RequestParam Long id)
	{
		Optional<ProductEntity> product = productRepository.findById(id);
		return product;
	}
	public void updateProduct(Long proId,ProductEntity product)
	{
		Optional<ProductEntity> existingProduct = productRepository.findById(proId);
		if(existingProduct.isPresent())
		{
			double price = product.getProPrice();
			String category = product.getProCategory();
			double disPrice = 0.0;
			
			switch(category)
			{
			case "mobile" :
				disPrice=price*0.1;
				break;
			case "laptop" :
				disPrice=price*0.15;
				break;
			case "printer" :
				disPrice=price*0.2;
				break;
			case "camera" :
				disPrice=price*0.25;
				break;
			
			}
			
		ProductEntity productToUpdate = existingProduct.get();
		productToUpdate.setProName(product.getProName());
		productToUpdate.setProPrice(product.getProPrice());
		productToUpdate.setProDiscount(disPrice);
		productToUpdate.setProBrand(product.getProBrand());
		productToUpdate.setProDescription(product.getProDescription());
		productToUpdate.setProCategory(product.getProCategory());
		
		
		productRepository.save(productToUpdate);
		}
	}
	public ProductEntity findProductById(Long id) {
	    Optional<ProductEntity> optionalProduct = productRepository.findById(id);
	    return optionalProduct.orElse(null);
	}
}

	



	



