package shop.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mall.DTO.Board;
import shop.mall.entity.BoardEntity;
import shop.mall.repository.BoardRespository;
import shop.mall.service.BoardService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRespository boardRespository;
    private final BoardService boardService;

    @GetMapping("/board/new")
    public String createBoard(Model model) {
        model.addAttribute("board", new Board());
        return "board/board";
    }

    @PostMapping("/board/new")
    public String create(@Valid Board board, BindingResult result) {

        BoardEntity board1 = new BoardEntity();
        board1.setTitle(board.getTitle());
        board1.setContent(board.getContent());
        board1.setCreatedAt(LocalDateTime.now());
        boardService.join(board1);

        return "redirect:/";
    }

    @GetMapping("/boards")
    public String boards (Model model) {
        List<BoardEntity> boards = boardRespository.findAll();
        model.addAttribute("boards", boards);;
        return "board/boardList";
    }
}
