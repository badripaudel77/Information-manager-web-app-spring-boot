package info.keeper.repositories;

import info.keeper.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    //get contacts by user id
    @Query("FROM Contact c WHERE c.user.id =:userId")
    Page<Contact> findContactsByUser(@Param("userId") int userId, Pageable pageable);

    //get the contact by user id : as a list
    @Query("FROM Contact C WHERE C.user.id =:userId")
    List<Contact> allNotesByUserId(@Param("userId") int userId);
}
