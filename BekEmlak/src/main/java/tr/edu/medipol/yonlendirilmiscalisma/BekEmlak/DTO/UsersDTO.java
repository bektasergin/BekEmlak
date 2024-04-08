package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class UsersDTO {
    private Integer userid;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Date birthday;
    private String phone;
    private String picture;
    private Integer user_type= 0;

}

