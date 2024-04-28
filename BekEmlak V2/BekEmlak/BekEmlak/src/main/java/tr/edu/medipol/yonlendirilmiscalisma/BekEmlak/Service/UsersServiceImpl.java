package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Users;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.UsersRepository;



@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepo;

    @Override
    public Users addUser(Users users) {
        return usersRepo.save(users);
    }

    public boolean isUserExists(String email) {
        return usersRepo.existsByEmail(email);
    }



}
