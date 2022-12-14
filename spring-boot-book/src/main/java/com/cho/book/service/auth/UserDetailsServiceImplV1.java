package com.cho.book.service.auth;

import com.cho.book.model.UserRole;
import com.cho.book.model.UserVO;
import com.cho.book.persistence.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserDetailsServiceImplV1 implements UserDetailsService {
    private final UserDao userdao;

    public UserDetailsServiceImplV1(UserDao userdao) {
        this.userdao = userdao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("USER SERVICE : {}",username);

        /*
            findById() method 는 Optional type 의 VO 를 return 한다.
            Optional type 의 VO 에서 실제 UserVO 데이터를 Get 하기 위해서
            orElse() 를 사용하는데
            orElse() 매개변수로 blank UserVO 생성하여 주었다.
            만약 username 으로 select 한 결과가 없으면
            blank UserVO 를 얻게 된다.
            null check 를 하지 않아도 안전한 코드가 된다.
         */
        UserVO userVO = userdao.findById(username).orElse(UserVO.builder().build());
        if(! userVO.getUsername().equals(username)) {
            throw new UsernameNotFoundException(username + "이 없음");
        }
        log.debug("로그인한 사용자{}, " ,userVO);
        Set< UserRole> roleList = userVO.getUserRoles();
        log.debug("사용자 ROLE 정보 {}", roleList);
        return userVO;
    }
}
