package demo.restaurant.backend.repositories;

import demo.restaurant.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByFirstnameAndLastnameIgnoreCase(
            String firstname, String lastname);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findByEmail(String email);

    boolean existsByPhoneNumber(String number);
    boolean existsByEmail(String email);

}
