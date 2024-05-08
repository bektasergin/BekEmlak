package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Controller;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO.AnnouncementsDTO;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Announcements;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.AnnouncementsRepo;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service.AnnouncementsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        announcements.setPhone(announcementsDTO.getPhone());

        Announcements registeredAnnouncement = announcementsService.addAnnouncements(announcements);

        return registeredAnnouncement.getName() + " " + "Adında yeni bir ilan oluşturuldu!";

    }


    @GetMapping("/ilan")
    public List<Announcements> getAllAnnouncements() {
        return announcementsService.getAllAnnouncements();
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteAnnouncementByName(@PathVariable String name) {
        try {
            announcementsService.deleteAnnouncementByName(name);
            return new ResponseEntity<>("Announcement with name '" + name + "' deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete announcement with name '" + name + "'.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Announcements> updateAnnouncementByName(@PathVariable String name, @RequestBody AnnouncementsDTO updatedAnnouncement) {
        Announcements announcement = announcementsService.updateAnnouncementByName(name, updatedAnnouncement);
        if (announcement != null) {
            return ResponseEntity.ok(announcement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
