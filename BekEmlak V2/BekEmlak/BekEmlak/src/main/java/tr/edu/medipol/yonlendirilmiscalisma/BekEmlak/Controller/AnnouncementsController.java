package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Controller;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO.AnnouncementsDTO;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.AnnouncementsRepo;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.UsersRepository;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service.AnnouncementsService;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service.UsersService;

@RestController
@Slf4j
public class AnnouncementsController {
    @Autowired
    AnnouncementsService announcementsService;

    @Autowired
    AnnouncementsRepo announcementsRepo;

    @PostMapping("/addAnnouncement")
    public String addAnnouncement(@RequestBody @NotNull AnnouncementsDTO announcementsDTO){
        Announcements announcements = new Announcements();

        announcements.setName(announcementsDTO.getName());
        announcements.setPrice(announcementsDTO.getPrice());
        announcements.setLocation(announcementsDTO.getLocation());
        announcements.setExplanation(announcementsDTO.getExplanation());
        announcements.setImage(announcementsDTO.getImage());
        announcements.setStatus(announcementsDTO.getStatus());

        Announcements registeredAnnouncement = announcementsService.addAnnouncements(announcements);

        return registeredAnnouncement.getName() + " " + "Adında yeni bir ilan oluşturuldu!";

    }
}
