package mainpackage.service;

import mainpackage.dao.ClientsDAO;
import mainpackage.model.ClientAddresses;
import mainpackage.model.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ClientsService")
public class ClientsServiceImpl  implements ClientsService {

    @Autowired
    @Qualifier("ClientsDAO")
    private ClientsDAO clientsDAO;

    public List<ClientAddresses> getClientAddresses(String userLogin){
        return this.clientsDAO.getClientAddresses(userLogin);
    }

    @Override
    @Transactional
    public Clients findClientByLogin(String clientLogin){return this.clientsDAO.findClientByLogin(clientLogin);}

    @Override
    @Transactional
    public void addClient (Clients client){this.clientsDAO.addClient(client);}

    @Override
    @Transactional
    public void updateClient (Clients client){this.clientsDAO.updateClient(client);}

    @Override
    @Transactional
    public Clients getClientById(int clientId){return  this.clientsDAO.getClientById(clientId);}

    @Override
    @Transactional
    public List<Clients> listClients(){return  this.clientsDAO.listClients();}
}
