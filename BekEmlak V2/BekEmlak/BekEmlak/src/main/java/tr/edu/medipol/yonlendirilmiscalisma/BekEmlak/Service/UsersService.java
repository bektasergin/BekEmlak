package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;
import org.springframework.stereotype.Service;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Users;

@Service
public interface UsersService {
    Users addUser(Users users);

    boolean isUserExists(String email);

}
