package mainpackage;

import mainpackage.configuration.PersistenceJPAConfig;
import mainpackage.service.CustomersService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext  annotationConfigApplicationContext =
               new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);

        //Create Spring application context
       // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring-servlet.xml");

        //Get service from context. (service's dependency (CustomersDAO) is autowired in CustomersService)
        //CustomersService customersService = ctx.getBean(CustomersService.class);

       CustomersService customersService = annotationConfigApplicationContext.getBean(CustomersService.class);

       System.out.println("listAll: " + customersService.listCustomers());

        //ctx.close();
        annotationConfigApplicationContext.close();

    }
}
