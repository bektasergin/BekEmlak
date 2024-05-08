package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;

import java.util.Optional;

public interface AnnouncementsRepo extends JpaRepository<Announcements, Integer> {
    Announcements findByName(String name);
}
