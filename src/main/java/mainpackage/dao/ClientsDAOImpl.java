package mainpackage.dao;

import mainpackage.model.ClientAddresses;
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

    @Override
    public void updateClient (Clients client){//must be improved
       Clients clientDb = (Clients) em.find(Clients.class, client.getClientId());
       clientDb.setFirstName(client.getFirstName());
       clientDb.setLastName(client.getLastName());
       clientDb.setBirthDate(client.getBirthDate());
       clientDb.setEmail(client.getEmail());

        ClientAddresses clientAddress = new ClientAddresses();
        clientAddress=client.getClientAddress();
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
    public Clients getClientById(int clientId){

        Query query = em.createQuery("from Clients where client_id=:clientId");

        List<Clients> res =query.setParameter("clientId", clientId).getResultList();//doesn't work if there more
        //than one Cart for same client in Cart table

        return  (Clients) query.setParameter("clientId", clientId).getSingleResult();
    }

    @Override
    public List<Clients> listClients(){
        return em.createQuery("FROM Clients c").getResultList();
    }
}

