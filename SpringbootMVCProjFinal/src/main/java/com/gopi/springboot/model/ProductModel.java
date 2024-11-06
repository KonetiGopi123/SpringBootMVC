package com.gopi.springboot.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel 
{
	@NotBlank(message = "Product name is required")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters")
    private String proName;

    // Product Price
    @NotNull(message = "Product price is required")
    @Min(value = 0, message = "Product price must be greater than or equal to zero")
    private double proPrice;

    // Product Brand
    @NotBlank(message = "Product brand is required")
    @Size(min = 2, max = 30, message = "Product brand must be between 2 and 30 characters")
    private String proBrand;

    // Product Description
    @NotBlank(message = "Product description is required")
    @Size(min = 10, max = 200, message = "Product description must be between 10 and 200 characters")
    private String proDescription;

    // Product Category
    @NotBlank(message = "Product category is required")
    private String proCategory;
	
	
}
