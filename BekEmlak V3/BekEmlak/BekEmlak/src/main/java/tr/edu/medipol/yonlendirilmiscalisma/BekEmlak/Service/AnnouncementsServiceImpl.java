package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;

import ch.qos.logback.core.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO.AnnouncementsDTO;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Users;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.AnnouncementsRepo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class AnnouncementsServiceImpl implements AnnouncementsService{
    @Autowired
    AnnouncementsRepo announcementsRepo;

    @Override
    public Announcements addAnnouncements(Announcements announcements) {
        return announcementsRepo.save(announcements);
    }

    @Override
    public List<Announcements> getAllAnnouncements() {
        return announcementsRepo.findAll();
    }

    @Override
    public void deleteAnnouncementByName(String name) {
        Announcements announcement = announcementsRepo.findByName(name);
        announcementsRepo.delete(announcement);

    }

    @Override
    public Announcements updateAnnouncementByName(String name, AnnouncementsDTO updatedAnnouncement) {
        Announcements announcement = announcementsRepo.findByName(name);
        if (announcement != null) {
            // Güncelleme yapılacaksa DTO'dan gelen verilerle güncelle
            announcement.setName(updatedAnnouncement.getName());
            announcement.setPrice(updatedAnnouncement.getPrice());
            announcement.setLocation(updatedAnnouncement.getLocation());
            announcement.setExplanation(updatedAnnouncement.getExplanation());
            announcement.setImage(updatedAnnouncement.getImage());
            announcement.setPhone(updatedAnnouncement.getPhone());
            // Güncellenmiş ilanı kaydet
            return announcementsRepo.save(announcement);
        }
        return null;
    }

}
