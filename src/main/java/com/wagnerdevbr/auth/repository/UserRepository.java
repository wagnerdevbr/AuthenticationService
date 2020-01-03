package com.wagnerdevbr.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wagnerdevbr.auth.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	@Query("select o from Users o where upper(trim(o.login)) = upper(:login)")
	User findByLogin(String login);

	@Query("select o from Users o where upper(trim(o.email)) = upper(:email)")
	User findByEmail(String email);

}
