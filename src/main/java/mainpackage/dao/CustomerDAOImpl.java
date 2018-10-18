package mainpackage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mainpackage.model.Customers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Component

@Repository("CustomersDAO")
//@Transactional
public class CustomerDAOImpl implements CustomersDAO {

    @PersistenceContext
    private EntityManager em;

    public void persist(Customers customers) {
        em.persist(customers);
    }

    //@Transactional
    public List<Customers> listCustomers() {
        return em.createQuery("SELECT c FROM Customers c").getResultList();
    }

}
