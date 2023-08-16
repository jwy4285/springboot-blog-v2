package shop.mtcoding.blogv2.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
 * save()인서트, findById()프라이머리키 조회, findAll() 전체조회, count() 갯수셀때, deleteById()
 * CRUD중에 업데이트만 빼고 다 있음 함수를 만들 필요가없음 CRUD 메서드를 주는애
 */
// 스프링이 실행될 때, BoardRepository의 구현체가 IoC 컨테이너에 생성된다.   
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // select id, title, content, user_id from board_tb b inner join user_tb u on
    // b.user_id = u.id;
    // fetch를 붙여야 *를 한다. fetch를 붙이면 객체안에 있는거를 다 조회하는거
    @Query("select b from Board b join fetch b.user")
    List<Board> mFindAll();
}
