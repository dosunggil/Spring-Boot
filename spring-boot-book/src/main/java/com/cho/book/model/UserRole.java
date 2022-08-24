package com.cho.book.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Builder
@Entity
@Table(name = "tbl_authorities")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String username;
    private String rolename;

    /*
        두 테이블을 1:N 관계로 설정할 경우
        Master class 에 @OneToMany 를 설정하고
        관계 연결되는 클래스에 @ManyToOne 을 설정해 주어야 한다.
        
        name ) UserRole.username 칼럼과
        referencedColumnName ) UserVO.username 칼럼을 서로 연결

        insertable = false, updatable = false 이 속성을 false 로 설정한 이유
        tbl_users 나 tbl_authorities 테이블에 insert 나 update 가 발생할 경우
        동시에 실행하지 마라

        읽기 전용으로만 사용하겠다.
     */
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username",  insertable = false, updatable = false)
    private UserVO userVO;

    @Override
    public String toString() {
        return "UserRole{" +
                "seq=" + seq +

                ", rolename='" + rolename + '\'' +

                '}';
    }
}
