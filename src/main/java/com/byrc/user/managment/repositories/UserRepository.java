package com.byrc.user.managment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.byrc.user.managment.entities.User;

/**
 * @ClassName: UserRepository
 * @author SKG
 * @since: 2022-02-05
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT dbh FROM User dbh  WHERE dbh.email = :email ")
	Optional<User> findUserByEmail(@Param("email") final String email);

}
