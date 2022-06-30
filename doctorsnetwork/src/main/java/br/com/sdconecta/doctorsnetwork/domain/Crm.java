package br.com.sdconecta.doctorsnetwork.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "crm")
public class Crm {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@NotBlank
		@Size(max = 45)
		private String crm;
		
		@NotBlank
		@Size(max = 2)
		private String uf;
		
		@Size(max = 255)
		private String specialty;
		
		@NotBlank
		@ManyToOne
		private User user;
		
		public Crm() {}
		
		public Crm(@NotBlank @Size(max = 45) String crm, @NotBlank @Size(max = 2) String uf,
				@Size(max = 255) String specialty, @NotBlank User user) {
			super();
			this.crm = crm;
			this.uf = uf;
			this.specialty = specialty;
			this.user = user;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCrm() {
			return crm;
		}

		public void setCrm(String crm) {
			this.crm = crm;
		}

		public String getUf() {
			return uf;
		}

		public void setUf(String uf) {
			this.uf = uf;
		}

		public String getSpecialty() {
			return specialty;
		}

		public void setSpecialty(String specialty) {
			this.specialty = specialty;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
}
