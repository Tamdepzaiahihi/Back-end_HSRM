package com.HSRMApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "managers")
public class ManagerGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String firstName;

    private String lastName;

    private Integer numberCard;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private Character sex;

    private String placeOfOrigin;

    private String nationality;

    @JsonFormat(pattern="yyyy-MM-dd")
    private  Date dateOfExpiry;

    private  String placeOfResidence;

    private  Integer phoneNumber;

    private String email;

    private Integer bankNumber;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
