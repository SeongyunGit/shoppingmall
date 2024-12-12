package shop.mall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mall.DTO.Board;
import shop.mall.entity.BoardEntity;
import shop.mall.repository.BoardRespository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    private final BoardRespository boardRespository;

    public BoardService(BoardRespository boardRespository) {
        this.boardRespository = boardRespository;
    }

    public List<BoardEntity> findAll() {
        return boardRespository.findAll();
    }

    public Optional<BoardEntity> findById(Long boardId) {
        return boardRespository.findById(boardId);
    }

    public BoardEntity join(BoardEntity board) {
        return boardRespository.save(board);
    }
}
