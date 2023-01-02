package com.jbk.RestDemo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Data
@FieldNameConstants
public class PhoneNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pId;
    private long mobileNo;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "emplId",nullable = false)
    private Employee employee;

}
