package com.bca.ecommerce.service;

import com.bca.ecommerce.Exception.StockException;
import com.bca.ecommerce.entity.Cart;
import com.bca.ecommerce.entity.Product;
import com.bca.ecommerce.entity.User;
import com.bca.ecommerce.repository.CartRepository;
import com.bca.ecommerce.repository.ProductRepository;
import com.bca.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    
    @Transactional
    public String addToCart(Optional<User> user, Integer productId, int quantity) {

        Product product = productRepository.findProductForUpdate(productId);

        if(product == null){
            throw new StockException("Product not found");
        }

        // ✅ Check stock
        if(product.getStock() < quantity){
            throw new StockException("Not enough stock available. Only " + product.getStock() + " items left in stock!");
        }

        // ✅ Reduce stock
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        // ✅ Add to cart
        Cart cart = new Cart();
        cart.setUser(user.get());
        cart.setProduct(product);
        cart.setQuantity(quantity);

        cartRepository.save(cart);
        
        return "SUCCESS";
    }


    @Override
    public List<Cart> getUserCart(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user);
    }

    @Transactional
    public void clearCart(User user) {

//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        cartRepository.deleteByUser(user);
    	
    	List<Cart> cartItems = cartRepository.findByUser(user);

        for(Cart cart : cartItems){

            Product product = cart.getProduct();

            // restore stock
            product.setStock(
                product.getStock() + cart.getQuantity()
            );

            productRepository.save(product);
        }

        // now clear cart
        cartRepository.deleteAll(cartItems);

    }
    
}
