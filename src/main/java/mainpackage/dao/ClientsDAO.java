package mainpackage.dao;

import mainpackage.model.Clients;

public interface ClientsDAO {
    Clients findClientByLogin(String login);

    void addClient (Clients client);
}
