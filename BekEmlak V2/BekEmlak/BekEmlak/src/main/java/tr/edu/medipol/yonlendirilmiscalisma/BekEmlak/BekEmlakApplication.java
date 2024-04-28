package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Users;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.UsersRepository;

@SpringBootApplication
public class BekEmlakApplication implements CommandLineRunner {

	@Autowired
	UsersRepository usersRepository;


	public static void main(String[] args) {
		SpringApplication.run(BekEmlakApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
