package vn.iotstar.Demo2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.Demo2.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}