package com.cho.book.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/*
    도서 정보 관리를 위하여 객체 추상화
    도서명, 출판사, 저자, 발행연도, 가격 등의 항목이 필요로 하다.
    이러한 항목을 기준으로 VO 클래스를 디자인하자.
    
    JPA 의 Entity 로 등록을 하고
    물리적 table 로 구성하기
 */
@Entity
@Table(name = "tbl_books", schema = "bootdb")
public class BookVO {

    @Id // VO 클래스를 Entity 로 등록을 하면 반드시 Id(PK) 를 지정해야 한다.
    @Column(length = 13)
    private String isbn;

    @Column(length = 125, nullable = false)
    private String title;


    private String company;
    private String author;

    @Column(length = 10)
    private String pub_date;

    @Column(nullable = true)
    private int price;
}
