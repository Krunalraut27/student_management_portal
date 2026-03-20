package com.student.management.Repository;

import com.student.management.Entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserTable,Long> {

    UserTable findByEmail(String email);

}
