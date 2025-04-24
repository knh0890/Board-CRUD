package com.example.board.time;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 테이블을 만들지 않고, 자식 클래스가 db에 맵핑될 때 컬럼으로 같이 포함
@EntityListeners(AuditingEntityListener.class) // 엔티티의 변경 사항(생성, 수정 시간 등)을 자동으로 기록
@Getter
public abstract class Time {
    // abstract로 상속을 통해서 기능 제공하기 위해
    // Time 클래스로 객체 생성하는것을 방지하기 위해 abstract사용

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate; // 작성일
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifieDate; // 수정일
}
