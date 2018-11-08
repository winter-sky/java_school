package mainpackage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.model.Params;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemsEditControllerTest extends ControllersTestBase {
    /** */
    private static final Logger log = LoggerFactory.getLogger(ItemsEditControllerTest.class);

    @Test
    public void testAddItem() throws Exception {
        // Choose some category.
        Categories cat = getAllCategories().get(3);

        Params param = new Params();

        param.setAuthor("Author");
        param.setLanguage("Language");
        param.setFormat("Format");

        Items item = new Items();

        item.setAvailableCount(33);
        item.setItemName("Test Item");
        item.setPic("/resources/Kawaii_Doodle_Cuties.jpg");
        item.setVolume("Test volume");
        item.setWeight(0.005);
        item.setPrice(5.55);
        item.setCategory(cat);
        item.setParams(param);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(item);

        log.info("JSON request prepared: " + requestJson);

//        mockMvc.perform(post("/additem").contentType(MediaType.APPLICATION_JSON_UTF8)
//            .sessionAttr("item", item)).andExpect(status().isOk());
          mockMvc.perform(post("/additem").contentType(MediaType.APPLICATION_JSON_UTF8)
              .content(requestJson)).andExpect(status().isOk());
    }
}
