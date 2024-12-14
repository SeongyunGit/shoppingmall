package shop.mall.DTO;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
public class KakaoPayReadyV0 {

    private String tid;
    private String next_redirect_pc_url;
    private Date created_at;
}
