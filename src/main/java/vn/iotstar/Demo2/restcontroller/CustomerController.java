package vn.iotstar.Demo2.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import vn.iotstar.Demo2.entity.Customer;
import vn.iotstar.Demo2.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
	
	 
    private final CustomerRepository customerRepository;

    // Constructor injection
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @PostConstruct
    public void init() {
        if (customerRepository.count() == 0) {
            Customer c1 = new Customer();
            c1.setName("Nguyễn Hữu Trung");
            c1.setEmail("trungnhspkt@gmail.com");
            customerRepository.save(c1);

            Customer c2 = new Customer();
            c2.setName("Hữu Trung");
            c2.setEmail("truonghuu@gmail.com");
            customerRepository.save(c2);
        }
    }
    
    // Endpoint công khai, không yêu cầu xác thực
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello là guest");
    }

    // Yêu cầu role ADMIN
    @GetMapping("/customer/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Customer>> customerList() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}