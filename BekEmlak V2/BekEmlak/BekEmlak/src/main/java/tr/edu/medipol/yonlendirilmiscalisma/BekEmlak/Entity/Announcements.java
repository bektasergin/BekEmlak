package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Announcements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int announcementID;
    private String name;
    private float price;
    private String location;
    private String explanation;
    private String image;
    private String status;
}
