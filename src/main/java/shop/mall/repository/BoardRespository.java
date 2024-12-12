package shop.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.mall.DTO.Board;
import shop.mall.entity.BoardEntity;

@Repository
public interface BoardRespository extends JpaRepository<BoardEntity, Long> {
}
