package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Announcements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer announcementid;
    private String name;
    private float price;
    private String location;
    private String explanation;
    private String image;
    private String phone;
}
