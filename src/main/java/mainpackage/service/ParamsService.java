package mainpackage.service;

import mainpackage.model.Items;
import mainpackage.model.Params;

import java.util.List;

public interface ParamsService {
    List<String> listAuthors();
    List<String> listLanguages();
    List<String> listFormats();

//    List<Items> searchItemsbyAuthor(String author);

    List<Items> listItems(int categoryId);

    List<Params> listParams();

    List<Items> listItemsByParam(String paramAuthor);
    List<Items> searchItemsByLanguageParam (String paramLanguage);
}
