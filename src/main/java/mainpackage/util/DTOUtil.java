package mainpackage.util;

import mainpackage.dto.CategoryDTO;
import mainpackage.dto.ItemDTO;
import mainpackage.model.Categories;
import mainpackage.model.Items;

import java.util.stream.Collectors;

public class DTOUtil {
    public static ItemDTO toDTO(Items item) {
        ItemDTO dto = new ItemDTO();

        dto.setItemId(item.getItemId());
        dto.setItemName(item.getItemName());
        dto.setCategory(toDTO(item.getCategory()));
        dto.setWeight(item.getWeight());
        dto.setAvailableCount(item.getAvailableCount());

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
}
