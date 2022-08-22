package com.example.laptopservice.repository;

import com.example.laptopservice.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {

    Optional<List<Laptop>> findByClientId(Integer id);
}
