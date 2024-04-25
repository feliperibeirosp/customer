package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAllCustomers(){
        List<CustomerDTO> customers = new ArrayList<>();

        customerRepository.findAll().stream().forEach(
                item->{
                    customers.add(mapCustomerEntityToDto(item));
                }
        );

        return customers;
    }

    public void createCustomer(CustomerDTO customerDTO){

        customerRepository.persist(mapCustomerDtoToEntity(customerDTO));

    }

    public void changeCustomer(Long id, CustomerDTO customerDTO){

        CustomerEntity customerEntity = customerRepository.findById(id);

        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setAge(customerDTO.getAge());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setPhone(customerDTO.getPhone());

        customerRepository.persist(customerEntity);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }


    private CustomerDTO mapCustomerEntityToDto(CustomerEntity customer){

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setAddress(customer.getAddress());
        customerDTO.setAge(customer.getAge());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());

        return customerDTO;
    }


    private CustomerEntity mapCustomerDtoToEntity(CustomerDTO customer){

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setAddress(customer.getAddress());
        customerEntity.setAge(customer.getAge());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setName(customer.getName());
        customerEntity.setPhone(customer.getPhone());

        return customerEntity;
    }
}
