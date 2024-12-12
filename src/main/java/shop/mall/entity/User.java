package shop.mall.entity;

import jakarta.persistence.*;
import lombok.*;
import shop.mall.DTO.UserRole;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime createDate;

    @PrePersist
    public void crateDate() {
        this.createDate = LocalDateTime.now();
    }
}
