package shop.mtcoding.projectjobplan.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@NoArgsConstructor
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
  
    private String birthdate;
    private Character gender; // 'M' or 'F'
    private String phoneNumber;
    private String address;
    private String email;

    // employer, 사업자 항목 nullable
    private Boolean isEmployer; // 사업자인지 userId, employerId
    private String employerIdNumber; // 사업자번호
    private String businessName; // 기업이름
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String username, String password, String name, String birthdate, Character gender, String phoneNumber, String address, String email, Boolean isEmployer, String employerIdNumber, String businessName, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.isEmployer = isEmployer;
        this.employerIdNumber = employerIdNumber;
        this.businessName = businessName;
        this.createdAt = createdAt;
    }
}
