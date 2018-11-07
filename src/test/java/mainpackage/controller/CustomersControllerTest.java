package mainpackage.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CustomersControllerTest extends ControllersTestBase {
    @Test
    public void testCustomer() throws Exception  {
        mockMvc.perform(get("/listcustomer")).andExpect(status().isOk()).andDo(print())
                .andExpect(view().name("listcustomers"));
    }
}
