package com.renaldinurmazid.userrestapi.repository;

import com.renaldinurmazid.userrestapi.entity.User;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> 
{
    //Post Mapping
    @Override
    @Transactional
    User save(User user);
    void deleteById(Long userId);
}
