package shop.mall.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import shop.mall.DTO.KakaoPayApprovalV0;
import shop.mall.DTO.KakaoPayReadyV0;

import shop.mall.config.PrincipalDetails;
import shop.mall.entity.CartItem;
import shop.mall.repository.CartItemRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Service
@Log
@RequiredArgsConstructor
public class KakaoPay {
    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyV0 kakaoPayReadyV0;
    private KakaoPayApprovalV0 kakaoPayApprovalV0;
    private String realTotalPrice;
    private List<CartItem> cartItems;
    private final CartService cartService;


    public String kakaoPayReady(String totalPrice, List<CartItem> cartItemList) {
        RestTemplate restTemplate = new RestTemplate();
        realTotalPrice = totalPrice;
        cartItems = cartItemList;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "bde87881baa87957b8f06894c399da02");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid","TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", "Mall");
        params.add("quantity", "1");
        params.add("total_amount", totalPrice);
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyV0 = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyV0.class);
            log.info("" + kakaoPayReadyV0);
            return kakaoPayReadyV0.getNext_redirect_pc_url();
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "/pay";
    }

    public KakaoPayApprovalV0 kakaoPayInfo(String pg_token, PrincipalDetails principalDetails) {
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "bde87881baa87957b8f06894c399da02");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyV0.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", realTotalPrice);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalV0 = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalV0.class);
            log.info("cartItems" + cartItems.size());
            for (CartItem cartItem : cartItems) {
                log.info("cartItemId " + cartItem.getItem().getId());
                cartService.delete(principalDetails, cartItem.getItem().getId());
            }
            log.info("" + kakaoPayApprovalV0);
            return kakaoPayApprovalV0;
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
