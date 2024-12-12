package shop.mall.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Board {

    private String title;
    private String content;
    private LocalDateTime createdAt;

}
