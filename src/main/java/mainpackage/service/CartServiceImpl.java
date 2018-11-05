package mainpackage.service;

import mainpackage.dao.CartDAO;
import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CartService")
public class CartServiceImpl implements  CartService{

    @Autowired//???
    @Qualifier("CartDAO")
    private CartDAO cartDAO;

    @Override
    @Transactional
    public List<Items> getGuestShoppingCart(){return this.cartDAO.getGuestShoppingCart();}

    @Override
    @Transactional
    public List<Items> getUsersShoppingCart(String userLogin){
        return  this.cartDAO.getUsersShoppingCart(userLogin);}

    @Override
    @Transactional
    public void addItemToGuestCart(int itemId, int guestCartId){this.cartDAO.addItemToGuestCart(itemId,guestCartId);}

    @Override
    @Transactional
    public Cart createGuestCart (){return this.cartDAO.createGuestCart();}

    @Override
    @Transactional
    public Cart createUserCart (Clients client){return  this.cartDAO.createUserCart(client);}
}
