package vn.iotstar.Demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import vn.iotstar.Demo3.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

