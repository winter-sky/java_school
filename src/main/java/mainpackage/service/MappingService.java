package mainpackage.service;

import mainpackage.dto.CategoryDTO;
import mainpackage.dto.ItemDTO;
import mainpackage.dto.ParamDTO;
import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.model.Params;

public interface MappingService {

    ItemDTO itemEntityToItemDTO(Items item);

    Items itemDTOtoItemEntity(ItemDTO item);

    ParamDTO paramEntityToParamDTO(Params param);

    Params paramDTOtoParamEntity(ParamDTO param);

    CategoryDTO categoryEntityToCategoryDTO(Categories category);

    Categories categoryDTOtoCategoryEntity(Categories category);
}
