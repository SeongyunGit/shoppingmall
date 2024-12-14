package shop.mall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mall.entity.Cart;
import shop.mall.entity.CartItem;
import shop.mall.entity.Item;
import shop.mall.entity.User;
import shop.mall.repository.CartItemRepository;
import shop.mall.repository.CartRepository;
import shop.mall.repository.ItemRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
    }

    public void addCart(User user, Item newItem, int amount) {
        Cart cart = cartRepository.findByUserId(user.getId());

        if (cart == null) {
            cart = Cart.createCart(user);
            cartRepository.save(cart);
        }

        Optional<Item> item1 = itemRepository.findById(newItem.getId());
        Item item = item1.get();
        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, item, amount);
            cartItemRepository.save(cartItem);
        } else {
            CartItem update = cartItem;
            update.setCart(cartItem.getCart());
            update.setItem(cartItem.getItem());
            update.addCount(amount);
            update.setCartCount(update.getCartCount());
            cartItemRepository.save(update);
        }

        cart.setCount(cart.getCount()+amount);

    }

    public List<CartItem> allUserCartView(Long id) {
        System.out.println("id"+ id);
        System.out.println("cartItemRepository"+cartItemRepository.findAllByCartId(id));
        return cartItemRepository.findAllByCartId(id);
    }
}
