package com.cho.data.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

// VO 클래스를 JPA 의 Data Table 화 하기 위한 선언
@Entity // : 지금부터 이 클래스는 Entity 설계를 위한 도구이다.

// bootDB 라는 Database 에 tbl_users 라는 이름으로
// UserVO 를 참조하여 table 이 있다고 가정 또는 강제 생성.
@Table(name = "tbl_users" , schema = "bootDB")
public class UserVO {

    // seq BIGINT PRIMARY KEY AUTO_INCREMENT
    @Id // PK 선언
    // MySQL 의 Auto_increment 설정과 같다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // username VARCHAR(20) NOT NULL UNIQUE
    @Column(columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String username;

    // DB 종류에 관계없이 문자열 255자로 설정
    // NOT NULL
    @Column(length =255, nullable = false)
    private String password;
    
    @Column(length = 125)
    private String email;
    
    // @Column 을 지정하지 않으면 default 로
    // String type = VARCHAR(255)
    @Column(length = 20)
    private String phone;
    private String address;

    @Column(length = 20)
    private String realname;

    @Column(length = 20)
    private String nickname;
}



