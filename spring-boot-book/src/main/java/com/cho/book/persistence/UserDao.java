package com.cho.book.persistence;

import com.cho.book.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserVO, String> {
}
