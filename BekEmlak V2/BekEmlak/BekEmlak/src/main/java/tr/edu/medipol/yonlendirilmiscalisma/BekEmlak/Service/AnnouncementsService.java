package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;

import org.springframework.stereotype.Service;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;
@Service
public interface AnnouncementsService {
    Announcements addAnnouncements(Announcements announcements);
}
