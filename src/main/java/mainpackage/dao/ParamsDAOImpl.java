package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.Params;
import mainpackage.util.Util;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository("ParamsDAO")
public class ParamsDAOImpl implements ParamsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> listAuthors() {
        Query query = em.createQuery("from Params");

        List<Params> listParams = query.getResultList();

        List<String> listAuthors = new ArrayList<>();

        for (Params p : listParams) {
            listAuthors.add(p.getAuthor());
        }

        return Util.removeDuplicates(listAuthors);
    }

    @Override
    public List<String> listLanguages() {
        Query query = em.createQuery("from Params");

        List<Params> listParams = query.getResultList();

        List<String> listAuthors = new ArrayList<>();

        for (Params p : listParams) {
            listAuthors.add(p.getLanguage());
        }

        return Util.removeDuplicates(listAuthors);
    }

    @Override
    public List<String> listFormats() {
        Query query = em.createQuery("from Params");

        List<Params> listParams = query.getResultList();

        List<String> listAuthors = new ArrayList<>();

        for (Params p : listParams) {
            listAuthors.add(p.getFormat());
        }

        return Util.removeDuplicates(listAuthors);
    }

    @Override
    public List<Items> listItemsByParam(String paramAuthor) {
        Query query = em.createQuery("from Params");

        List<Params> listParams = query.getResultList();

        List<Items> listItems = new ArrayList<>();

        for (Params p : listParams) {
            //System.out.println(p.getAuthor());
            if ((p.getAuthor()).equals(paramAuthor)) {
                listItems.add(p.getItem());
            }
        }

        return listItems;
    }

    @Override
    public List<Items> searchItemsByLanguageParam(String paramLanguage) {
        Query query = em.createQuery("from Params");

        List<Params> listParams = query.getResultList();

        List<Items> listItems = new ArrayList<>();

        for (Params p : listParams) {
            if ((p.getLanguage()).equals(paramLanguage)) {
                listItems.add(p.getItem());
            }
        }

        return listItems;
    }

    @Override
    public List<Items> searchItemsByFormatParam (String paramFormat){
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Items> query = builder.createQuery(Items.class);

        Root<Items> r = query.from(Items.class);

        Predicate predicate = builder.conjunction();

        predicate = builder.and(predicate, builder.like(r.get("params").get("format"), paramFormat));

        query.where(predicate);

        return em.createQuery(query).getResultList();
    }
}
