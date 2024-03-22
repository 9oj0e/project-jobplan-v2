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

    // 회원 정보
    private String username;
    private String password;

    // 개인 정보
    private String name;
    private String birthdate;
    private Character gender; // 'M' or 'F'
    private String phoneNumber;
    private String address;
    private String email;

    /*
    // 이력 정보 (회원에서 처리할거면)
    private String schoolName;
    private String major;
    private String educationLevel; // 고졸/초대졸/대졸
    */

    // 회사 정보
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
        /*
        this.schoolName = schoolName;
        this.major = major;
        this.educationLevel = educationLevel;
        */
        this.isEmployer = isEmployer;
        this.employerIdNumber = employerIdNumber;
        this.businessName = businessName;
        this.createdAt = createdAt;
    }

    public void update(UserRequest.UpdateDTO requestDTO) {
        this.password = requestDTO.getPassword();
        this.gender = requestDTO.getGender();
        this.phoneNumber = requestDTO.getPhoneNumber();
        this.address = requestDTO.getAddress();
        this.email = requestDTO.getEmail();
        /*
        this.schoolName = schoolName;
        this.major = major;
        this.educationLevel = educationLevel;
        */
        this.employerIdNumber = requestDTO.getEmployerIdNumber();
        this.businessName = requestDTO.getBusinessName();
    }

}
