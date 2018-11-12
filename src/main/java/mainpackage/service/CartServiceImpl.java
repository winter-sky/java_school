package mainpackage.service;

import mainpackage.dao.CartDAO;
import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

@Service("CartService")
public class CartServiceImpl implements  CartService {
    private CartDAO cartDAO;

    private ItemsService itemsService;

    private ClientsService clientsService;

    @Autowired
    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Autowired
    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Autowired
    public void setClientsService(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @Override
    @Transactional
    public List<Items> getUsersShoppingCart(String userLogin) {
        Clients client = clientsService.findClientByLogin(userLogin);

        return client.getCart() != null ? client.getCart().getItems() : null;
    }

    @Override
    @Transactional
    public void addItemToGuestCart(Integer itemId, int guestCartId){
        Cart cart = cartDAO.getCart(guestCartId);

        Items item = itemsService.findItemById(itemId);

        if (cart.getItems() == null) {
            cart.setItems(Arrays.asList(item));
        } else {
            cart.addItem(item);
        }

        // TODO: update DB?
    }

    @Override
    @Transactional
    public Cart createGuestCart (){return this.cartDAO.createGuestCart();}

    @Override
    @Transactional
    public Cart createUserCart (Clients client){return  this.cartDAO.createUserCart(client);}
}
