package com.jbk.RestDemo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.Set;

@Entity
@Data
@FieldNameConstants


public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int emplId;
	private String emplName;
	private long emplSalary;
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER,mappedBy = PhoneNo.Fields.employee,orphanRemoval = true,cascade = CascadeType.ALL)

	private Set<PhoneNo> phoneNo ;


}
