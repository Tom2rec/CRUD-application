package task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import task.model.Customer;
import task.data.CustomerRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String showCustomerList(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    @ModelAttribute(value = "customer")
    public Customer newCustomer(){
        return new Customer();
    }
    @PostMapping
    public String addCustomer(@Valid Customer customer, BindingResult result,
                              Model model, RedirectAttributes atts){
        if(result.hasErrors()){
            return "index";
        }

        customerRepository.save(customer);
        atts.addFlashAttribute("message", "Customer created.");
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable("id") long id, @Valid Customer customer, BindingResult result,
                                 Model model, RedirectAttributes atts){
        if(result.hasErrors()){
            return "index";
        }
        System.out.println(customer.getId());
        Optional<Customer> fromDB = customerRepository.findById(id);
        if(fromDB.isPresent()){
            Customer customerFromDB = fromDB.get();

            customerFromDB.setName(customer.getName());
            customerFromDB.setSurname(customer.getSurname());
            customerFromDB.setEmail(customer.getEmail());
            customerFromDB.setAddress(customer.getAddress());
            customerFromDB.setZip(customer.getZip());
            customerFromDB.setCity(customer.getCity());
            customerFromDB.setCountry(customer.getCountry());
            customerFromDB.setPhone(customer.getPhone());
            customerFromDB.setAdditionalInfo(customer.getAdditionalInfo());

            customerRepository.save(customerFromDB);
            atts.addFlashAttribute("message", "Customer updated");
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes atts){
        customerRepository.deleteById(Long.valueOf(id));
        atts.addFlashAttribute("message", "Customer deleted.");

        return "redirect:/";
    }
}
