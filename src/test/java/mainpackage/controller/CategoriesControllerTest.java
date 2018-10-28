package mainpackage.controller;

import mainpackage.configuration.PersistenceJPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class CategoriesControllerTest extends ControllersTestBase {
    @Test
    public void testCategories() throws Exception {
        mockMvc.perform(get("/listcategories")).andDo(print())
                .andExpect(view().name("catalog"));
    }

    @Test
    public void testCategorties2() throws Exception {
        MvcResult res = mockMvc.perform(get("/listcategories")).andDo(print())
                .andExpect(view().name("catalog")).andReturn();

        assertNotNull(res);

        MockHttpServletResponse response = res.getResponse();

        System.out.println("Response ContentType: " + response.getContentType());
        System.out.println("Response ContentLengthLong: " + response.getContentLengthLong());
        System.out.println("Response ContentLength: " + response.getContentLength());
        System.out.println("Response ContentAsByteArray: " + response.getContentAsByteArray());
        System.out.println("Response ContentAsByteArray: length " + response.getContentAsByteArray().length);

        assertNotNull(response);

        assertNotNull(response.getContentAsString());
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testShowItemByCategory() throws Exception {
        mockMvc.perform(get("/showitemsbycategory", 1)).andDo(print());
    }
}
