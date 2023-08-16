package shop.mtcoding.blogv2.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest // 모든 Repository, EntityManger
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        Page<Board> boardPG = boardRepository.findAll(pageable);
        ObjectMapper om = new ObjectMapper();

        // ObjectMapper는 boardPG객체의 getter를 호출하면서 json을 만든다
        String json = om.writeValueAsString(boardPG); // 자바객체를 JSON으로 변환

    }

    @Test
    public void mfindAll_test() {
        boardRepository.findAll();
    }

    @Test
    public void findAll_test() {
        System.out.println("조회 직전");
        List<Board> boardList = boardRepository.findAll();
        System.out.println("조회 후 : Lazy");
        // 행 : 5개 -> 객체 : 5개
        // 각행 : Board (id=1, title=제목1, content=내용1, created_at=날짜, User(id=1)) 레이지로
        // 땡겨올때
        // 레이지는 프라이머리키로만 땡겨옴
        System.out.println(boardList.get(0).getId()); // 1번 (조회 x)
        System.out.println(boardList.get(0).getUser().getId()); // 1번 (조회 x)

        // Lazy Loading - 지연로딩
        // 연관된 객체에 null을 참조하려고 하면 조회가 일어남
        System.out.println(boardList.get(0).getUser().getUsername()); // null -> ssar

    }

    @Test
    public void save_test() {
        // 비영속 객체
        Board board = Board.builder()
                .title("제목6")
                .content("내용6")
                .user(User.builder().id(1).build())
                .build();

        // 영속 객체
        boardRepository.save(board); // insert 자동으로 실행됨
        // 디비데이터와 동기화됨
        System.out.println(board.getId());
        // 테스트 끝나면 항상 롤백됨

    }

}
