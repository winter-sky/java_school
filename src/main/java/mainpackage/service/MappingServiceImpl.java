package mainpackage.service;

import mainpackage.dto.CategoryDTO;
import mainpackage.dto.ItemDTO;
import mainpackage.dto.ParamDTO;
import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.model.Params;
import org.springframework.stereotype.Service;

@Service
public class MappingServiceImpl implements MappingService {


    @Override
    public ItemDTO itemEntityToItemDTO(Items item) {
        ItemDTO dto = new ItemDTO();
        dto.setAvailableCount(item.getAvailableCount());
        // yada yada
        return dto;
    }

    @Override
    public Items itemDTOtoItemEntity(ItemDTO item) {
        Items entity = new Items();
        entity.setItemId(item.getItemId());

        return null;
    }

    @Override
    public ParamDTO paramEntityToParamDTO(Params param) {
        return null;
    }

    @Override
    public Params paramDTOtoParamEntity(ParamDTO param) {
        return null;
    }

    @Override
    public CategoryDTO categoryEntityToCategoryDTO(Categories category) {
        return null;
    }

    @Override
    public Categories categoryDTOtoCategoryEntity(Categories category) {
        return null;
    }
}
