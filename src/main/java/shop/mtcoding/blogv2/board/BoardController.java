package shop.mtcoding.blogv2.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1. 데이터 받기
    // 2. 인증체크
    // 3. 유효성 검사
    // 4. 핵심로직 호출(서비스) 비지니스로직이나 핵심로직을 서비스에서 트랜잭션 관리함
    // 5. view or data 응답 (뷰가될수도 있고 데이터가 될수도 있음)
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        System.out.println("title : " +saveDTO.getTitle());
        System.out.println("content : " +saveDTO.getContent());
        return "redirect:/";
    }

}
