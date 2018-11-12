package mainpackage.service;

import mainpackage.model.ClientAddresses;
import mainpackage.model.Clients;

import java.util.List;

public interface ClientsService {
    Clients findClientByLogin(String clientLogin);

    void addClient (Clients client);

    void updateClient (Clients client);

    Clients getClientById(int clientId);

    List<Clients> listClients();

    List<ClientAddresses> getClientAddresses(String userLogin);
}
