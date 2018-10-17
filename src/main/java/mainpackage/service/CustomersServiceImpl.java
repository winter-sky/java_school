package mainpackage.service;

import mainpackage.dao.CustomersDAO;
import mainpackage.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private CustomersDAO customersDAO;

    @Transactional
    public List<Customers> listCustomers(){return customersDAO.listCustomers();}



}
