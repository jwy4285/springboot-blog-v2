package shop.mtcoding.blogv2.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.Script;
import shop.mtcoding.blogv2.user.User;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/{id}/updateForm")
    public String update(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardService.게시물수정하기(id);
        // System.out.println("테스트1 : " + boardService.게시물수정하기(id).getTitle());
        // System.out.println("테스트1 : " + boardService.게시물수정하기(id).getContent());
        request.setAttribute("board", board);
        return "board/updateForm";
    }

    @GetMapping("/board/{id}") // 보드 상세보기
    public String deta(@PathVariable Integer id, Model model) {
        Board board = boardService.상세보기(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    // localhost:8080?page=1&keyword=바나나
    @GetMapping("/")
    public String main(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        Page<Board> boardPG = boardService.게시물목록보기();
        request.setAttribute("boardPG", boardPG);
        request.setAttribute("prevPage", boardPG.getNumber() - 1);
        request.setAttribute("nexPage", boardPG.getNumber() + 1);
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1. 데이터 받기
    // 2. 인증체크 (:TODO)
    // 3. 유효성 검사(:TODO)
    // 4. 핵심로직 호출(서비스) 비지니스로직이나 핵심로직을 서비스에서 트랜잭션 관리함
    // 5. view or data 응답 (뷰가될수도 있고 데이터가 될수도 있음)
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        boardService.글쓰기(saveDTO, 1);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
            boardService.삭제하기(id);
            return Script.href("/");
        } catch (Exception e) {
            return Script.back("삭제 실패");
        }

    }
}