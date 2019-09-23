package workshop.spring.boot.workshopspringboot.models;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import workshop.spring.boot.workshopspringboot.validations.ValidRut;

 
public class Student {

	@Id
	private int id;

	@ValidRut
	private String rut;

	@NotNull
	private String name;
 
	//@JsonProperty(access = Access.WRITE_ONLY)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
 
 	
//	@Override
//	public String toString() {
//		Integer size = null;
//		if(this.getCourse() != null) {
//			size = this.getCourse().size();
//		}
//		
//		return new StringBuilder().append("id:").append(this.id).append(",")
//							.append("rut:").append(this.rut).append(",")
//							.append("name:").append(this.name).append(",")
//							.append("size:").append(size).toString();
//	}
	
	 

}
