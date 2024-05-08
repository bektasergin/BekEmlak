package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnnouncementsDTO {
    private Integer announcementid;
    private String name;
    private float price;
    private String location;
    private String explanation;
    private String image;
    private String phone;
}
