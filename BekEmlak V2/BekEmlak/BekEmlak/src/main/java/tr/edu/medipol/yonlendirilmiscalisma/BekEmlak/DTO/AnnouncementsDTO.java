package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AnnouncementsDTO {
    private int announcementID;
    private String name;
    private float price;
    private String location;
    private String explanation;
    private String image;
    private String status;
}
