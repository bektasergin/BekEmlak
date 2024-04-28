package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

    @Data
    @AllArgsConstructor
    @ToString
    public class LoginDTO {
        private String password;
        private String email;
    }
