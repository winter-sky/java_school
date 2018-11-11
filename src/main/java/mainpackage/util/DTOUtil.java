package mainpackage.util;

import mainpackage.dto.CategoryDTO;
import mainpackage.dto.ClientDTO;
import mainpackage.dto.ItemDTO;
import mainpackage.dto.ParamDTO;
import mainpackage.model.Categories;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import mainpackage.model.Params;

import java.util.stream.Collectors;

public class DTOUtil {
    public static ItemDTO toDTO(Items item) {
        ItemDTO dto = new ItemDTO();

        dto.setItemId(item.getItemId());
        dto.setItemName(item.getItemName());
        dto.setPrice(item.getPrice());
        dto.setVolume(item.getVolume());
        dto.setWeight(item.getWeight());
        dto.setAvailableCount(item.getAvailableCount());
        dto.setPic(item.getPic());

        return dto;
    }

    public static CategoryDTO toDTO(Categories cat) {
        CategoryDTO dto = new CategoryDTO();

        dto.setCategoryName(cat.getCategoryName());
        dto.setCategoryId(cat.getCategoryId());
        dto.setCategoryLevel(cat.getCategoryLevel());
        dto.setParentId(cat.getParentId());
        // TODO: infinite recursion
        //dto.setCategory(toDTO(cat.getCategory()));
        //dto.setCategories(cat.getCategories().stream().map(DTOUtil::toDTO).collect(Collectors.toList()));

        return dto;
    }

    public static ClientDTO toDTO(Clients client) {
        ClientDTO dto = new ClientDTO();

        dto.setClientId(client.getClientId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setBirthDate(client.getBirthDate());
        dto.setEmail(client.getEmail());

        return dto;
    }

    public static ParamDTO toDTO(Params param) {
        return null;
    }

    public static Items fromDTO(ItemDTO dto) {
        Items item = new Items();

        item.setItemId(dto.getItemId());
        item.setItemName(dto.getItemName());
        item.setPrice(dto.getPrice());
        item.setVolume(dto.getVolume());
        item.setWeight(dto.getWeight());
        item.setAvailableCount(dto.getAvailableCount());
        item.setPic(dto.getPic());

        return item;
    }

    public static Params fromDTO(ParamDTO param) {
        return null;
    }

    public static Categories fromDTO(Categories category) {
        return null;
    }
}
