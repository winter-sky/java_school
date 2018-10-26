package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.Params;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository("ParamsDAO")
public class ParamsDAOImpl implements ParamsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> listAuthors(){
        Query query = em.createQuery("from Params");
        List<Params> listParams = query.getResultList();
        List<String> listAuthors=new ArrayList<>();
        for(Params p:listParams){
            listAuthors.add( p.getAuthor());
        }

        List<String> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(listAuthors));

        return listWithoutDuplicates;
    }

    @Override
    public List<Items> listItems(int categoryId){
        Query query = em.createQuery("from Items where item_category=:categoryId");
        return  (List<Items>)query.setParameter("categoryId", categoryId).getResultList();
    }

    @Override
    public List<String> listLanguages(){
        Query query = em.createQuery("from Params");
        List<Params> listParams = query.getResultList();
        List<String> listAuthors=new ArrayList<>();
        for(Params p:listParams){
            listAuthors.add( p.getLanguage());
        }

        List<String> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(listAuthors));

        return listWithoutDuplicates;
    }

    @Override
    public List<String> listFormats(){
        Query query = em.createQuery("from Params");
        List<Params> listParams = query.getResultList();
        List<String> listAuthors=new ArrayList<>();
        for(Params p:listParams){
            listAuthors.add( p.getFormat());
        }

        List<String> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(listAuthors));

        return listWithoutDuplicates;
    }

//    @Override
//    public List<Items> searchItemsbyAuthor(String paramAuthor){
//
//        Query query = em.createQuery("from Params where author=:paramAuthor");
//        List<Params> listParams = query.setParameter("paramAuthor", paramAuthor).getResultList();
//        System.out.println("ну и где это дерьмо");
//        List<Items> list=new ArrayList<>();
//        for(Params p:listParams){
//            list.add(p.getItem());
//        }
//        return list;
//    }

    @Override
    public List<Params> listParams(){
        Query query = em.createQuery("from Params");
        return query.getResultList();
    }

    @Override
    public List<Items> listItemsByParam(String paramAuthor){
        Query query = em.createQuery("from Params");
        List<Params> listParams = query.getResultList();
        System.out.println(listParams);
        List<Items> listItems=new ArrayList<>();
        for(Params p:listParams){
            //System.out.println(p.getAuthor());
            if((p.getAuthor()).equals(paramAuthor)){
                System.out.println(p.getAuthor());
               listItems.add(p.getItem());
            }
        }
        return listItems;
    }

    @Override
    public List<Items> searchItemsByLanguageParam (String paramLanguage){
        Query query = em.createQuery("from Params");
        List<Params> listParams = query.getResultList();
        System.out.println(listParams);
        List<Items> listItems=new ArrayList<>();
        for(Params p:listParams){

            if((p.getLanguage()).equals(paramLanguage)){
                System.out.println(p.getLanguage());
                listItems.add(p.getItem());
            }
        }
        return listItems;
    }
}
