package demo.restaurant.backend.repositories;

import demo.restaurant.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByFirstnameAndLastnameIgnoreCase(
            String firstname, String lastname);

    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);

}
