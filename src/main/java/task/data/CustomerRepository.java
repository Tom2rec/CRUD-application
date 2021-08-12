package task.data;

import org.springframework.data.repository.CrudRepository;
import task.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
