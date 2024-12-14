package shop.mall.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayApprovalV0 {

    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private AmountV0 amount;
    private CardV0 card_info;
    private String item_name, item_code, payload;
    private Integer quantity, tax_free_amount, vat_amount;
    private Date created_at, approved_at;
}
