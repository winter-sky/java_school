package mainpackage.dao;

import mainpackage.model.ClientAddresses;
import mainpackage.model.Clients;
import mainpackage.model.Logins;
import mainpackage.model.Orders;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository("ClientsDAO")
public class ClientsDAOImpl implements ClientsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Clients findClientByLogin(String clientLogin) {
        Query query = em.createQuery("from Logins WHERE login = :clientLogin");

        Logins l = (Logins)query.setParameter("clientLogin", clientLogin).getSingleResult();

        return l.getClient();
    }

    @Override
    public void addClient(Clients client) {
        em.persist(client);
    }

    @Override
    public void updateClient (Clients client){//must be improved
//       Clients clientDb = (Clients) em.find(Clients.class, client.getClientId());//must be changed
//       clientDb.setFirstName(client.getFirstName());
//       clientDb.setLastName(client.getLastName());
//       clientDb.setBirthDate(client.getBirthDate());
//       clientDb.setEmail(client.getEmail());
//
//        ClientAddresses clientAddress = new ClientAddresses();
//        clientAddress=client.getClientAddress();
//        ClientAddresses clientAddressesDb = clientDb.getClientAddress();
//        clientAddressesDb.setCountry(clientAddress.getCountry());
//        clientAddressesDb.setCity(clientAddress.getCity());
//        clientAddressesDb.setZipCode(clientAddress.getZipCode());
//        clientAddressesDb.setStreet(clientAddress.getStreet());
//        clientAddressesDb.setBuilding(clientAddress.getBuilding());
//        clientAddressesDb.setApartment(clientAddress.getApartment());
//
//        Logins login = client.getLogin();
//        Logins loginDb = clientDb.getLogin();
//        loginDb.setPassword(login.getPassword());
    }

    @Override
    public Clients getClientById(int clientId) {
        Query query = em.createQuery("from Clients where client_id=:clientId");

        return (Clients) query.setParameter("clientId", clientId).getSingleResult();
    }

    @Override
    public List<Clients> listClients() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Clients> q = cb.createQuery(Clients.class);

        Root<Clients> root = q.from(Clients.class);

        q.select(root);

        TypedQuery<Clients> query = em.createQuery(q);

        return query.getResultList();
    }
}

