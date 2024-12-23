package shop.mall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mall.config.PrincipalDetails;
import shop.mall.entity.Cart;
import shop.mall.entity.CartItem;
import shop.mall.entity.Item;
import shop.mall.entity.User;
import shop.mall.repository.CartItemRepository;
import shop.mall.repository.CartRepository;
import shop.mall.repository.UserRepository;
import shop.mall.service.AuthService;
import shop.mall.service.CartService;
import shop.mall.service.ItemService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final ItemService itemService;

    @GetMapping("/cart/{id}")
    public String cartHome(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails.getUser().getId()" + principalDetails.getUser().getId());
        System.out.println(id);
        if (principalDetails.getUser().getId() == id) {
            Optional<User> user1 = userRepository.findById(id);
            User user = user1.get();
            Cart userCart = user.getCart();
            if (userCart!=null) {
                List<CartItem> cartItems = cartService.allUserCartView(userCart.getId());
                System.out.println("cartItems = " + cartItems);
                int totalPrice = 0;
                for (CartItem cartItem : cartItems) {
                    totalPrice += cartItem.getCartCount() * cartItem.getItem().getPrice();
                }

                model.addAttribute("totalPrice", totalPrice);
                model.addAttribute("totalCount", userCart.getCount());
                model.addAttribute("cartItems", cartItems);
                model.addAttribute("user", user);
                return "cart/userCart";
            } else {
                return "cart/userCart";
            }
        }
        else {
            return "redirect:/";
        }
    }

    @PostMapping("/cart/{id}/{itemId}")
    public String addCartItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, @RequestParam("amount") int amount) {
        Optional<User> user1 = userRepository.findById(id);
        User user = user1.get();
        Item item = itemService.itemView(itemId);
        cartService.addCart(user, item, amount);
        return "redirect:/item/view/{itemId}";
    }

    @PostMapping("/cart/update")
    public String updateCart(@RequestParam("cartCount") int cartCount,
                             @RequestParam("itemId") Long itemId,
                             @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = principalDetails.getUser().getId();
        cartService.update(principalDetails,itemId, cartCount);
        return "redirect:/cart/" + id;
    }

    @GetMapping("/cart/delete/{itemId}")
    public String deleteCart(@PathVariable("itemId") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        cartService.delete(principalDetails, id);
        return "redirect:/cart/"+principalDetails.getUser().getId();
    }
}
