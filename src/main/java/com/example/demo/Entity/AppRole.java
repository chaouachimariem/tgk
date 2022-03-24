package com.example.demo.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
@Data

public class AppRole {



@Override
public String toString() {
return "AppRole [id=" + id + ", roleName=" + roleName + "]";
}
public AppRole() {
super();
// TODO Auto-generated constructor stub
}
public AppRole(Long id, String roleName) {
super();
this.id = id;
this.roleName = roleName;
}
@Id
@GeneratedValue
private Long id;
private String roleName;
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
public String getRoleName() {
return roleName;
}
public void setRoleName(String roleName) {
this.roleName = roleName;
}
}

