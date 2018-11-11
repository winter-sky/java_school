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
    public int getClientOrderQuantity(Clients client) {
        List<Orders> listClientOrders = new ArrayList<>();

        if (client.getOrders() != null) {
            listClientOrders = client.getOrders();
        }

        return listClientOrders.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Clients findClientByLogin(String clientLogin) {
        Query query = em.createQuery("from Logins");

        List<Logins> logins = (List<Logins>)query.getResultList();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(clientLogin)) {
                return l.getClient();
            }
        }

        return null;
    }

    @Override
    public void addClient(Clients client) {
        em.persist(client);
    }

    @Override
    public void updateClient(Clients client) {//must be improved
        Clients clientDb = em.find(Clients.class, client.getClientId());

        clientDb.setFirstName(client.getFirstName());
        clientDb.setLastName(client.getLastName());
        clientDb.setBirthDate(client.getBirthDate());
        clientDb.setEmail(client.getEmail());

        ClientAddresses clientAddress = client.getClientAddress();
        ClientAddresses clientAddressesDb = clientDb.getClientAddress();
        clientAddressesDb.setCountry(clientAddress.getCountry());
        clientAddressesDb.setCity(clientAddress.getCity());
        clientAddressesDb.setZipCode(clientAddress.getZipCode());
        clientAddressesDb.setStreet(clientAddress.getStreet());
        clientAddressesDb.setBuilding(clientAddress.getBuilding());
        clientAddressesDb.setApartment(clientAddress.getApartment());

        Logins login = client.getLogin();
        Logins loginDb = clientDb.getLogin();
        loginDb.setPassword(login.getPassword());
    }

    @Override
    public Clients getClientById(int clientId) {
        Query query = em.createQuery("from Clients where client_id=:clientId");

        // TODO: currently unused, delete?
        List<Clients> res = query.setParameter("clientId", clientId).getResultList();//doesn't work if there more
        //than one Cart for same client in Cart table

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

