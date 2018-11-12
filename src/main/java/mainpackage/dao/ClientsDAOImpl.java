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
import java.util.*;

@Repository("ClientsDAO")
public class ClientsDAOImpl implements  ClientsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ClientAddresses> getClientAddresses(String userLogin){
        return  new ArrayList<>(findClientByLogin(userLogin).getClientAddresses());
    }



    @Override
    public int getClientOrderQuantity(Clients client){
        List<Orders> listClientOrders = new ArrayList<>();
        if (client.getOrders()!=null){
            listClientOrders=client.getOrders();
        }
        int orderQuantity = listClientOrders.size();
        return orderQuantity;
    }

    @Override
    public List<Clients> getListTopClients(){
        List<Clients> listAllClients=new ArrayList<>();
        listAllClients = listClients();

        Map<Clients, Integer> clientOrderQuantityMap = new HashMap();
        for(Clients client:listAllClients){
            int quantity = getClientOrderQuantity(client);
            clientOrderQuantityMap.put(client,quantity);
        }

//        Map<Clients, Integer> result = new LinkedHashMap<>();
//        orderItemQuantityMap.entrySet().stream()
//                .sorted(Map.Entry.<Items, Integer>comparingByValue().reversed()).limit(10)
//                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
//
//
//        Set<Items> set = result.keySet();
//
//        List<Items>list = new ArrayList<>(set);

        return null;
    }

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
    public Clients getClientById(int clientId){

        Query query = em.createQuery("from Clients where client_id=:clientId");

        List<Clients> res =query.setParameter("clientId", clientId).getResultList();//doesn't work if there more
        //than one Cart for same client in Cart table

        return  (Clients) query.setParameter("clientId", clientId).getSingleResult();
    }

    @Override
    public List<Clients> listClients(){

        //return em.createQuery("FROM Clients c").getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Clients> q = cb.createQuery(Clients.class);
        Root<Clients> root = q.from(Clients.class);
        q.select(root);
        TypedQuery<Clients> query = em.createQuery(q);
        List<Clients> listAllClients= query.getResultList();

        return listAllClients;
    }
}

