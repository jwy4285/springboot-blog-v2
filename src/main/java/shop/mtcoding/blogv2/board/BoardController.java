package shop.mtcoding.blogv2.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.user.User;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

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
        Board board = Board.builder()
                .title(saveDTO.getTitle())
                .content(saveDTO.getContent())
                .user(User.builder().id(1).build())
                .build();

        return "redirect:/";
    }

}
