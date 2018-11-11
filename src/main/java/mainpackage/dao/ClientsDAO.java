package mainpackage.dao;

import mainpackage.model.Clients;

import java.util.List;

public interface ClientsDAO {

    Clients findClientByLogin(String login);

    void addClient (Clients client);

    void updateClient (Clients client);

    Clients getClientById(int clientId);

    List<Clients> listClients();
}
