package mainpackage.service;


import mainpackage.dao.ParamsDAO;
import mainpackage.model.Items;
import mainpackage.model.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ParamsService")
public class ParamsServiceImpl implements  ParamsService {

    @Autowired//???
    @Qualifier("ParamsDAO")
    private ParamsDAO paramsDAO;

    @Override
    @Transactional
    public List<String> listAuthors(){ return paramsDAO.listAuthors();};

    @Override
    @Transactional
    public List<String> listLanguages(){ return paramsDAO.listLanguages();};

    @Override
    @Transactional
    public List<String> listFormats(){ return paramsDAO.listFormats();};

    @Override
    @Transactional
    public List<Items> listItems(int categoryId){return  paramsDAO.listItems(categoryId);}

//    @Override
//    @Transactional
//    public List<Items> searchItemsbyAuthor(String author){return paramsDAO.searchItemsbyAuthor(author);}

    @Override
    @Transactional
    public List<Params> listParams(){return  paramsDAO.listParams();}

    @Override
    @Transactional
    public List<Items> listItemsByParam(String paramAuthor){return paramsDAO.listItemsByParam(paramAuthor);}

    public List<Items> searchItemsByLanguageParam (String paramLanguage){return paramsDAO.searchItemsByLanguageParam
            (paramLanguage);}
}
