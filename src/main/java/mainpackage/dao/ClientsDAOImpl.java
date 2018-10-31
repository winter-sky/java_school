package mainpackage.dao;

import mainpackage.model.Clients;
import mainpackage.model.Logins;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("ClientsDAO")
public class ClientsDAOImpl implements  ClientsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Clients findClientByLogin(String clientLogin) {
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(clientLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }

        return client;
    }

    @Override
    public void addClient (Clients client){
        em.persist(client);
    }
}

