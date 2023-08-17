package shop.mtcoding.blogv2.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.user.User;
/*
 * 1. 비지니스 로직 처리(핵심로직)
 * 2. 트랜잭션 관리
 * 3. 예외처리
 * 4. DTO 변환 (나중에할게)
 */

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, int sessionUserId) {
        Board board = Board.builder()
                .title(saveDTO.getTitle())
                .content(saveDTO.getContent())
                .user(User.builder().id(sessionUserId).build())
                .build();
        boardRepository.save(board);
    }

    public Page<Board> 게시물목록보기() {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);
    }

    public Board 상세보기(Integer id) {
        // board 만 가져오면 된다!!
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            return boardOP.get();
        } else {
            throw new RuntimeException(id + "는 찾을 수 없습니다.");
        }

    }

    @Transactional
    public void 삭제하기(Integer id) {
        try {
            boardRepository.deleteById(6);
        } catch (Exception e) {
            throw new RuntimeException("6번은 없어요");
        }

    }

    public Board 게시물수정하기(Integer id) {
        return boardRepository.findById(id).get();

    }

}