package shop.mall.controller;

import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mall.service.KakaoPay;

@Log
@Controller
public class KakaoPayController {

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaoPay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {}

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam("totalPrice") String totalPrice) {
        log.info("totalPrice" + totalPrice);
        log.info("kakaoPay post.....");
        return "redirect:" + kakaoPay.kakaoPayReady(totalPrice);
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        model.addAttribute("info", kakaoPay.kakaoPayInfo(pg_token));
    }
}
