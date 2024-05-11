package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO.UsersDTO;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    boolean existsByEmail(String email);

}

