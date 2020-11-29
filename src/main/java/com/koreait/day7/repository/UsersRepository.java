package com.koreait.day7.repository;

import com.koreait.day7.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
