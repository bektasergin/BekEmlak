package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Users;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.AnnouncementsRepo;

@Component
public class AnnouncementsServiceImpl implements AnnouncementsService{
    @Autowired
    AnnouncementsRepo announcementsRepo;

    @Override
    public Announcements addAnnouncements(Announcements announcements) {
        return announcementsRepo.save(announcements);
    }
}
