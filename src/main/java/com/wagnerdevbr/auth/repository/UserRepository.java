package com.wagnerdevbr.auth.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.wagnerdevbr.auth.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByLogin(String login);
}
