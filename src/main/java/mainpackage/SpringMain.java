package mainpackage;

import mainpackage.service.CustomersService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        //Create Spring application context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring-servlet.xml");

        //Get service from context. (service's dependency (ProductDAO) is autowired in ProductService)
        CustomersService customersService = ctx.getBean(CustomersService.class);


        //Test element list after rollback
        System.out.println("listAll: " + customersService.listCustomers());

        ctx.close();

    }
}
