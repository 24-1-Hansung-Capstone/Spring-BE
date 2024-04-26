package com.example.HansungCapstone.Repository.Login;

import com.example.HansungCapstone.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
}
