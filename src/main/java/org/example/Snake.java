package org.example;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "snake_data")
@Data
public class Snake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snake_id")
    private int sid;
    @Column(name = "snake_name")
    private String sname;
    private String food;

}
