package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;

import ch.qos.logback.core.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Announcements updateAnnouncementByName(@RequestParam("name") String name,
                                                  @RequestParam("updatedName") String updatedName,
                                                  @RequestParam("price") float updatedPrice,
                                                  @RequestParam("location") String updatedLocation,
                                                  @RequestParam("explanation") String updatedExplanation,
                                                  @RequestParam("image") MultipartFile updatedImage,
                                                  @RequestParam("phone") String updatedPhone) {
        Announcements announcement = announcementsRepo.findByName(name);
        if (announcement != null) {
            // Güncelleme yapılacaksa DTO'dan gelen verilerle güncelle
            announcement.setName(updatedName);
            announcement.setPrice(updatedPrice);
            announcement.setLocation(updatedLocation);
            announcement.setExplanation(updatedExplanation);

            // Handle image update
            try {
                announcement.setImage(updatedImage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle error
            }

            announcement.setPhone(updatedPhone);
            // Güncellenmiş ilanı kaydet
            return announcementsRepo.save(announcement);
        }
        return null;
    }

}
