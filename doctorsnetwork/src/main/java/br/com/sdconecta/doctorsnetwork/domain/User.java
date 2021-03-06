package br.com.sdconecta.doctorsnetwork.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(max = 255)
	@Column(unique = true)
	private String email;
	
	@NotBlank
	@Size(max = 255)
	@JsonIgnore
	private String password;
	
	@NotBlank
	@Size(max = 255)
	private String name;
	
	@NotBlank
	@Size(max = 255)
	private String surname;
	
	@NotBlank
	@Size(max = 255)
	private String mobilePhone;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Crm> crms;
	
	@Size(max = 255)
	private String lastSDConectaAuthorizationStatus;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User() {}
	
	public User(@NotBlank @Size(max = 255) String email, @NotBlank @Size(max = 255) String password,
			@NotBlank @Size(max = 255) String name, @NotBlank @Size(max = 255) String surname,
			@NotBlank @Size(max = 255) String mobilePhone) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.mobilePhone = mobilePhone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public List<Crm> getCrms() {
		return crms;
	}

	public void setCrms(List<Crm> crms) {
		this.crms = crms;
	}

	public String getLastSDConectaAuthorizationStatus() {
		return lastSDConectaAuthorizationStatus;
	}

	public void setLastSDConectaAuthorizationStatus(String lastSDConectaAuthorizationStatus) {
		this.lastSDConectaAuthorizationStatus = lastSDConectaAuthorizationStatus;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
