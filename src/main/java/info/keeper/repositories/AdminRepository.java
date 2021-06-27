package info.keeper.repositories;

import info.keeper.models.AdminMessage;
import info.keeper.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<AdminMessage, Integer> {
    @Override
    ArrayList<AdminMessage> findAll();
}
