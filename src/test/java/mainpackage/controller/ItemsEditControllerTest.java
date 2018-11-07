package mainpackage.controller;

import mainpackage.model.Items;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ItemsEditControllerTest extends ControllersTestBase {
    @Test
    public void testAddItem() throws Exception {
        Items item = new Items();

        item.setAvailableCount(33);
        item.setItemName("Test Item");
        item.setPic("/resources/Kawaii_Doodle_Cuties.jpg");
        item.setVolume("Test volume");
        item.setWeight(0.005);
        item.setPrice(5.55);

        mockMvc.perform(post("/additem").contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("item", item)).andExpect(status().isOk());
    }
}
