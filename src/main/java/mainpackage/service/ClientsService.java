package mainpackage.service;

import mainpackage.model.Clients;

public interface ClientsService {
    Clients findClientByLogin(String clientLogin);

    void addClient (Clients client);
}
