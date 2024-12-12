package shop.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.mall.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
