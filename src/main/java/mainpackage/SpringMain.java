package mainpackage;

import mainpackage.configuration.PersistenceJPAConfig;
import mainpackage.configuration.SecurityConfig;
import mainpackage.configuration.WebAppInit;
import mainpackage.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext  annotationConfigApplicationContext =
               new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);


        ItemsService itemsService = annotationConfigApplicationContext.getBean(ItemsService.class);

        //System.out.println("list all items from items table: " + itemsService.listItems());

        CategoriesService categoriesService = annotationConfigApplicationContext.getBean(CategoriesService.class);
//
//        System.out.println("list of categories with level 0 from categories table: " + categoriesService.listCategories());
//        ctx.close();
//        System.out.println("list of subcategories: " + categoriesService.listSubCategories(2));
//
//        System.out.println("list of subcategories: " + categoriesService.listAllCategories());
        //System.out.println("Find category by id : " + categoriesService.findCategoryById(9));

        ParamsService paramsService = annotationConfigApplicationContext.getBean(ParamsService.class);
//        System.out.println("Items by category : " + paramsService.listItems(13));
        System.out.println("Items by author : " + paramsService.listItemsByParam("Lewis Carroll"));
        System.out.println("Items by language : " + paramsService.searchItemsByLanguageParam("russian"));

        ClientsService clientsService=annotationConfigApplicationContext.getBean(ClientsService.class);
        System.out.println("Find client by login: " + clientsService.findClientByLogin("alice"));
        
        annotationConfigApplicationContext.close();

    }
}
