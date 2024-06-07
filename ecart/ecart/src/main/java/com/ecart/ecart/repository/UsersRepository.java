package com.ecart.ecart.repository;

import com.ecart.ecart.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value="select u from Users u where u.email=?1")
    Users findByEmail(String email);

    @Query(value="select u from Users u where u.email=?1 and u.password=?2")
    Users findByEmailAndPassword(String emailId, String password);
}
