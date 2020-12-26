package org.launchcode.bookshop.data;

import org.launchcode.bookshop.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends CrudRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    public Users getUserByUsername(@Param("username") String username);
}

