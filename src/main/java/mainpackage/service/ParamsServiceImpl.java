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
@Transactional
public class ParamsServiceImpl implements  ParamsService {

    @Autowired//???
    @Qualifier("ParamsDAO")
    private ParamsDAO paramsDAO;

    @Override
    public List<String> listAuthors(){ return paramsDAO.listAuthors();};

    @Override
    public List<String> listLanguages(){ return paramsDAO.listLanguages();};

    @Override
    public List<String> listFormats(){ return paramsDAO.listFormats();};

    @Override
    public List<Items> listItemsByParam(String paramAuthor){return paramsDAO.listItemsByParam(paramAuthor);}

    public List<Items> searchItemsByLanguageParam (String paramLanguage){return paramsDAO.searchItemsByLanguageParam
            (paramLanguage);}

    @Override
    public List<Items> searchItemsByFormatParam (String paramFormat){
        return  this.paramsDAO.searchItemsByFormatParam(paramFormat);}
}
