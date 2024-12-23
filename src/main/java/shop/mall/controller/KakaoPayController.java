package shop.mall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mall.config.PrincipalDetails;
import shop.mall.entity.CartItem;
import shop.mall.service.CartService;
import shop.mall.service.KakaoPay;

import java.util.List;

@Log
@Controller
@RequiredArgsConstructor
public class KakaoPayController {

    private final CartService cartService;

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaoPay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {}

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam("totalPrice") String totalPrice, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("cartId" + principalDetails.getUser().getCart().getId());
        List<CartItem> cartItems = cartService.allUserCartView(principalDetails.getUser().getCart().getId());
        log.info("totalPrice" + totalPrice);
        log.info("kakaoPay post.....");
        return "redirect:" + kakaoPay.kakaoPayReady(totalPrice, cartItems);
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        model.addAttribute("info", kakaoPay.kakaoPayInfo(pg_token, principalDetails));
    }
}
