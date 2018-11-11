package mainpackage.service;

import mainpackage.dto.ClientStatDTO;
import mainpackage.dto.ItemStatDTO;

import java.util.List;

public interface StatisticsService {
    List<ClientStatDTO> getTopClients(int count);

    List<ItemStatDTO> getTopItems(int count);
}
