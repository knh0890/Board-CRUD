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

    @CreatedDate // 엔티티가 처음 저장(생성)될 때 현재 시간이 자동으로 저장
    @Column(updatable = false) // 한번 생성된 이후에는 수정 불가하도록 지정
    private LocalDateTime createDate; // 작성일
    
    @LastModifiedDate // 엔티티가 수정될 때마다 현재 시간이 자동으로 갱신
    @Column(insertable = false) // 생성 시에는 이 컬럼을 넣지 않도록 지정
    private LocalDateTime modifieDate; // 수정일
}
