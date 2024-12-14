package shop.mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.mall.config.PrincipalDetails;
import shop.mall.entity.Item;
import shop.mall.entity.User;
import shop.mall.repository.UserRepository;
import shop.mall.service.AuthService;
import shop.mall.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/item/new") //상품 등록페이지
    public String itemSaveForm() {
        return "item/new";
    }

    @PostMapping("/item/new") // 상품 등록
    public String itemSave(@ModelAttribute Item item, @RequestParam("imgFile") MultipartFile imgFile) throws Exception {
        itemService.saveItem(item, imgFile);
        return "redirect:/";
    }

    @GetMapping("/item/itemAll")
    public String itemAll(Model model) {
        List<Item> itemAll = itemService.allItemView();
        model.addAttribute( "itemAll", itemAll);
        return "item/itemAll";
    }
    //상품 수정 페이지 GET
    @GetMapping("/item/modify/{id}")
    public String itemModifyForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.itemView(id));
        return "item/modify";
    }

    // 상품 수정 POST
    @PostMapping("/item/modify/complete/{id}")
    public String itemModify(Item item, @PathVariable("id") Long id) {
        itemService.itemModify(item, id);
        return "redirect:/";
    }

    // 상품상세 페이지 GET
    @GetMapping("/item/view/{id}")
    public String itemView(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        model.addAttribute(itemService.itemView(id));
        model.addAttribute("user", user);
        return "item/itemView";
    }

    // 상품 삭제 GET
    @GetMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        itemService.itemDelete(id);
        return "main";
    }

    @GetMapping("/user/itemAll/{id}")
    public String userItemAll(@PathVariable("id") Long id, Model model) {
        List<Item> userItems = itemService.userItem(id);
        model.addAttribute("userItems", userItems);
        return "item/userItems";
    }
}
