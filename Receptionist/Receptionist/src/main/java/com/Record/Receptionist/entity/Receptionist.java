package com.Record.Receptionist.entity;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Receptionist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date appointmentTime;
    private Date appointmentDate;
    private String description;
    private Long doctor_id;

}
