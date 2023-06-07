package com.example.fproject.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor

public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(columnDefinition = "varchar(25) not null")
    private Integer quantity ;


    @Column(columnDefinition = "varchar(25)")
    private Integer totalPrice = null;


    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "varchar(25) not null")
    private String dateReceived;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null check ( status='new' or status='inProgress' or status='completed')")
    private String status = "new";




    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser user;


    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

}
