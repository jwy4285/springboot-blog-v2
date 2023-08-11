package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * save()인서트, findById()프라이머리키 조회, findAll() 전체조회, count() 갯수셀때, deleteById()
 * CRUD중에 업데이트만 빼고 다 있음 함수를 만들 필요가없음 CRUD 메서드를 주는애
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
