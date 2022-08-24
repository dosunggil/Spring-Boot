package com.cho.book.service.impl;

import com.cho.book.model.UserRole;
import com.cho.book.model.UserVO;
import com.cho.book.persistence.UserDao;
import com.cho.book.persistence.UserRoleDao;
import com.cho.book.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplV1  implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    public UserServiceImplV1(PasswordEncoder passwordEncoder, UserDao userDao, UserRoleDao userRoleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }

    // 회원가입을 하기 위한 Service Method
    @Override
    @Transactional
    public UserVO join(UserVO userVO) {

        // tbl_users table 의 데이터 개수 확인
        Long userCount = userDao.count();
        List<UserRole> roleList = new ArrayList<>();

        if(userCount < 1) {

            /*
                Legacy 에서는 선택적으로 사용을 했으나
                boot 에서는 필수적으로 체크하는 항목이다.
             */
            userVO.setEnabled(true);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);

            roleList.add(UserRole.builder().username(userVO.getUsername())
                    .rolename("ROLE_ADMIN")
                    .build());
            roleList.add(UserRole.builder().username(userVO.getUsername())
                    .rolename("ROLE_USER")
                    .build());
        } else {
            userVO.setEnabled(false);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);

            roleList.add(UserRole.builder().username(userVO.getUsername())
                    .rolename("ROLE_USER")
                    .build());
        }
        
        // 사용자가 입력한 비밀번호를 암호화해서 저장
        String password = userVO.getPassword();
        String encPassword = passwordEncoder.encode(password);
        userVO.setPassword(encPassword);

        userDao.save(userVO);
        userRoleDao.saveAll(roleList);
        return null;
    }
}
