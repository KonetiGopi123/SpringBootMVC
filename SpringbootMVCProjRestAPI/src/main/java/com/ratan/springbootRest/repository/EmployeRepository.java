package com.ratan.springbootRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ratan.springbootRest.entity.EmployeEntity;


@Repository
public interface EmployeRepository extends JpaRepository<EmployeEntity,Long>{

}
