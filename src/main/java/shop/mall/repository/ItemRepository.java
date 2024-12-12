package shop.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.mall.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
