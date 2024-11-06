package com.gopi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopi.springboot.MVC.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long >{

}
