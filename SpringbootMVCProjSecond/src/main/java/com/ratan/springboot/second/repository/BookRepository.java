package com.ratan.springboot.second.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratan.springboot.second.Entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity,Long >{

}
