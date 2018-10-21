package mainpackage;

import mainpackage.configuration.PersistenceJPAConfig;
import mainpackage.configuration.WebAppInit;
import mainpackage.service.CategoriesService;
import mainpackage.service.CustomersService;
import mainpackage.service.ItemsService;
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

      // System.out.println("list all customers from customers table: " + customersService.listCustomers());

        ItemsService itemsService = annotationConfigApplicationContext.getBean(ItemsService.class);

        //System.out.println("list all items from items table: " + itemsService.listItems());

        CategoriesService categoriesService = annotationConfigApplicationContext.getBean(CategoriesService.class);

        System.out.println("list of categories with level 0 from categories table: " + categoriesService.listCategories());
        //ctx.close();
//        System.out.println("list of subcategories: " + categoriesService.listSubCategories(2));
//
//        System.out.println("list of subcategories: " + categoriesService.listAllCategories());
        annotationConfigApplicationContext.close();

    }
}
