package mainpackage.service;

import mainpackage.model.Customers;

import java.util.List;

public interface CustomersService {
    List<Customers> listCustomers();
    void addCustomer(Customers customer);
}
