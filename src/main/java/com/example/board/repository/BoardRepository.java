package com.example.board.repository;

import com.example.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
     // JPQL로 작성 시: 엔티티 기준으로 쿼리가 작성(DB에 의존하지 않고 테이블 구조가 변경 되어도 엔티티만 수정)
     // 더 객체지향적이고 유지보수에 유리한 구조
     @Modifying
     @Query("UPDATE Board b SET b.viewCount = b.viewCount + 1 WHERE b.idx = :idx")
     void viewCount(@Param("idx") Long idx);

     // 제목 검색
     Page<Board> findByTitleContaining(String title, Pageable pageable);
     // 내용 검색
     Page<Board> findByContentContaining(String content, Pageable pageable);
     // 작성자 검색
    Page<Board> findByIdContaining(String id, Pageable pageable);
}
