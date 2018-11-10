package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.Params;

import java.util.List;

public interface ParamsDAO {
    List<String> listAuthors();
    List<String> listLanguages();
    List<String> listFormats();


//    List<Params> listParams();//had not been used yet

   // List<Items> listItems(int categoryId);//had not been used yet

    List<Items> listItemsByParam(String paramAuthor);//searching items by author

    List<Items> searchItemsByLanguageParam (String paramLanguage);//search items by language param

    List<Items> searchItemsByFormatParam (String paramFormat);
}
