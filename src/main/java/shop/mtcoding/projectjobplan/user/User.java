package shop.mtcoding.projectjobplan.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    // 이력 정보
    private String schoolName;
    private String major;
    private String educationLevel; // 고졸/초대졸/대졸
    private String career; // 회사명+경력

    // 회사 정보
    private Boolean isEmployer; // 사업자인지 userId, employerId
    private String employerIdNumber; // 사업자번호
    private String businessName; // 기업이름

    @CreationTimestamp
    private Timestamp createdAt;

    @OrderBy("id desc")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Entity 객체의 변수명 == FK의 주인
    private List<Board> boards = new ArrayList<>();

    @OrderBy("id desc")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Entity 객체의 변수명 == FK의 주인
    private List<Resume> resumes = new ArrayList<>();


    @Builder
    public User(Integer id, String username, String password, String name, String birthdate, Character gender, String phoneNumber, String address, String email, String schoolName, String major, String educationLevel, String career, Boolean isEmployer, String employerIdNumber, String businessName, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.schoolName = schoolName;
        this.major = major;
        this.educationLevel = educationLevel;
        this.career = career;
        this.isEmployer = isEmployer;
        this.employerIdNumber = employerIdNumber;
        this.businessName = businessName;
        this.createdAt = createdAt;
    }
}
