package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.Address;
import com.SalesFlowSA.SalesFlow.model.Customer;
import com.SalesFlowSA.SalesFlow.model.DTO.CustomerDTO;
import com.SalesFlowSA.SalesFlow.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<Map<String, Object>> registerCustomer(CustomerDTO customerDTO){
        if(customerDoesNotExist(customerDTO)){
            try{
                Customer customer = new Customer();
                customer.setName(customerDTO.getName());
                customer.setLastName(customerDTO.getLastName());
                customer.setCpf(customerDTO.getCpf());
                customer.setEmail(customerDTO.getEmail());

                Address address = new Address();
                address.setCity(customerDTO.getAddress().getCity());
                address.setState(customerDTO.getAddress().getState());
                address.setCountry(customerDTO.getAddress().getCountry());
                address.setNeighborhood(customerDTO.getAddress().getNeighborhood());
                address.setStreet(customerDTO.getAddress().getStreet());
                address.setNumber(customerDTO.getAddress().getNumber());
                address.setZipCode(customerDTO.getAddress().getZipCode());

                customer.setAddress(address);
                customerRepository.save(customer);

                Map<String, Object> response = new HashMap<>();
                response.put("message","Client registered successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }catch (Exception e){

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Fail to register customer");
                response.put("error", e);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

        }else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fail to register customer");
            response.put("error", "Customer with the given CPF already exists.");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<?> searchAllCustomer(){
        try{
            List<Customer> customerList = new ArrayList<>();
            customerList = customerRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(customerList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to search all Customers");
        }
    }

    public ResponseEntity<?> searchCustomerByCpf(@PathVariable("cpf") String cpf){
        try{
            List<Customer> customerList = new ArrayList<>();
            customerList = customerRepository.findCustomerByCpf(cpf);
            return ResponseEntity.status(HttpStatus.OK).body(customerList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to search customer by CPF");
        }
    }

    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        try{
            customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            customerRepository.deleteById(id);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Customer id " + id + " deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("message","Fail to delete Customer id " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public boolean customerDoesNotExist(CustomerDTO customerDTO) {
        return customerRepository.findCustomerByCpf(customerDTO.getCpf()).isEmpty();
    }
}
