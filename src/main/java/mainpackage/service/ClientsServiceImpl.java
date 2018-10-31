package mainpackage.service;

import mainpackage.dao.ClientsDAO;
import mainpackage.model.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ClientsService")
public class ClientsServiceImpl  implements ClientsService {

    @Autowired
    @Qualifier("ClientsDAO")
    private ClientsDAO clientsDAO;

    @Override
    @Transactional
    public Clients findClientByLogin(String clientLogin){return this.clientsDAO.findClientByLogin(clientLogin);}

    @Override
    @Transactional
    public void addClient (Clients client){this.clientsDAO.addClient(client);}
}
