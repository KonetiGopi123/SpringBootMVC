package com.gopi.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.gopi.springboot.MVC.ProductEntity;
import com.gopi.springboot.model.ProductModel;
import com.gopi.springboot.service.ProductService;

import jakarta.validation.Valid;


@Controller
public class ProductController 
{
	@Autowired
	ProductService proService;
	
	
	//Get the product form 
   @GetMapping("/productform")
   public String getProductForm(Model model)
   {
	   //give the form with object
	   ProductModel productModel=new ProductModel();
	   //Adding the empty object into model to send the view 
	   model.addAttribute("productModel",productModel);
	   model.addAttribute("page", "productform");
		return "index";
	   
   }
   
   
   //
   @PostMapping("/saveProduct")
   public String saveProduct(
           @Valid ProductModel productModel,
           BindingResult bindingResult, Model model) {
       
       // Check if there are validation errors
       if (bindingResult.hasErrors()) {
           // If errors, return to the form with error messages
           return "add-product";
       }
       proService.saveProductData(productModel);
       return "success";
   }
   
   @GetMapping("/getProducts")
   public String getProducts(Model model)
   {
	   List<ProductEntity> products=proService.getAllProducts();
	   model.addAttribute("products",products);
	   model.addAttribute("page","getProducts");
	   return "index";
	   
   }
   
   //Delete the product data by using id
   @PostMapping("/delete/{id}")
   public String deleteProduct(@PathVariable("id") Long proId) {
       proService.deleteProductById(proId);
       return "redirect:/getProducts";
}
//
   @GetMapping("/products/editProduct/{id}")
	 public String editProduct(@PathVariable("id") Long proId,Model model)
	 {
		 Optional<ProductEntity>productOpt = proService.editProductById(proId);
		 if (productOpt.isPresent()) {
		        ProductEntity product = productOpt.get(); 
		        model.addAttribute("product", product);  
		       return "Edit-form";
		    } else {
		        return "getProducts";
		    }
	 }
   
//update the products data by using id
   @PostMapping("/update/{id}")
	  public String updateProduct(@PathVariable("id") Long proId, @ModelAttribute ProductEntity product) {
	        proService.updateProduct(proId, product);
	        return "redirect:/getProducts"; // Redirect to the products list page after update

}
   //search the product by using id
   @GetMapping("/searchProduct/{id}")
   public String searchProduct(@PathVariable("id") Long id, Model model) {
       ProductEntity product = proService.findProductById(id);
       if (product != null) {
           model.addAttribute("products", List.of(product)); // Return a list with the found product
       } else {
           model.addAttribute("products", List.of()); // Return an empty list if not found
       }
       return "product-list";

   }
   //Get the about page
   @GetMapping("/about")
   public String showAboutPage(Model model) {
   model.addAttribute("page","about");
       return "index"; 
   }
   //Get the contact page
   @GetMapping("/contact")
   public String showContactForm(Model model) {
       model.addAttribute("contact");
       model.addAttribute("page","contact");
       
       return "index"; 
       
   //Get the index form
   }
   @GetMapping("/")
   public String getHomepage(Model model)
   {
	   model.addAttribute("page","/");
	   
	   
	   return "index";
   }
   
   
   
   
}



   





