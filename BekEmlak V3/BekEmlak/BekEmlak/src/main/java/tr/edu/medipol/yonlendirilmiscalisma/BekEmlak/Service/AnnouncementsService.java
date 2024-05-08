package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;

import io.github.classgraph.Resource;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO.AnnouncementsDTO;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;

import java.io.IOException;
import java.util.List;

@Service
public interface AnnouncementsService {
    Announcements addAnnouncements(Announcements announcements);
    public List<Announcements> getAllAnnouncements();

    public void deleteAnnouncementByName(String name);

    public Announcements updateAnnouncementByName(String name, AnnouncementsDTO updatedAnnouncement);
}
