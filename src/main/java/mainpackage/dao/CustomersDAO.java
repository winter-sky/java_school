package mainpackage.dao;

import mainpackage.model.Customers;

import java.util.List;

public interface CustomersDAO {
    List<Customers> listCustomers();
    void addCustomer(Customers customer);
}
