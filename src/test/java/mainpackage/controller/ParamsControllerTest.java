package mainpackage.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ParamsControllerTest extends ControllersTestBase {
    @Test
    public void testSearchByAuthor() throws Exception {
        mockMvc.perform(get("/searchbyauthor", 1)).andDo(print());
    }
}
