package mainpackage.service;

import mainpackage.dao.ItemsDAO;
import mainpackage.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ItemsService")
public class ItemsServiceImpl implements ItemsService {

    @Autowired//???
    @Qualifier("ItemsDAO")
    private ItemsDAO itemsDAO;

    @Override
    @Transactional
    public List<Items> listItems(){return this.itemsDAO.listItems();}



}
