package shop.mall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BoardEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;



}
