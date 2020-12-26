package org.launchcode.bookshop.data;

import org.launchcode.bookshop.models.Event;
import org.launchcode.bookshop.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
}
