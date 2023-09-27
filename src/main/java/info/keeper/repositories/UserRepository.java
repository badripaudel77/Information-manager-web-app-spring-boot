package info.keeper.repositories;

import info.keeper.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email= :email")
    User findUserByUsername(@Param("email") String email);

    @Query("select u.id from User u where u.email= :email")
    int findUserIdByUsername(@Param("email") String email);
}
