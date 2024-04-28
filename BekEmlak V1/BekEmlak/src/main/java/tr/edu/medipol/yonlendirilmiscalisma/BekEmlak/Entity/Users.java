package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Date birthday;
    private String phone;
    private String picture;
    private Integer user_type = 0;
}

