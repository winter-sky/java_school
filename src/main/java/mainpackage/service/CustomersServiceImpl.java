package mainpackage.service;

import mainpackage.dao.CustomersDAO;
import mainpackage.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//@Component

@Service("CustomersService")
//@Transactional
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    @Qualifier("CustomersDAO")
    private CustomersDAO customersDAO;

    public void setCustomersDAO(CustomersDAO customersDAO) {
        this.customersDAO = customersDAO;
    }

    @Override
    @Transactional
    public List<Customers> listCustomers(){return customersDAO.listCustomers();}



}
