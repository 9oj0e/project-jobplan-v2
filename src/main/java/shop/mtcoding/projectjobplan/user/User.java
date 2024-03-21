package shop.mtcoding.projectjobplan.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
  
    private String birthdate; // Timestamp..
    private char gender; // 'M' or 'F'
    private String phoneNumber;
    private String address;
    private String email;

    // employer, 사업자 항목 nullable
    private Boolean isEmployer; // 사업자인지 userId, employerId
    private String employerIdNumber; // 사업자번호
    private String businessName; // 기업이름
    private Timestamp createdAt;
}
