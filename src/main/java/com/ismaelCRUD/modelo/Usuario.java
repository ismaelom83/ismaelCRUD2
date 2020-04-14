package com.ismaelCRUD.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */

	// entidad
	private static final long serialVersionUID = 6989612429797393220L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private long id;

	@Column
	@NotBlank
	@Size(min = 2, max = 20, message = "Numero de caracteres invalido")
	private String nombre;

	@Column
	@NotBlank
	@Size(min = 2, max = 50, message = "Numero de caracteres invalido")
	private String apellidos;

	@Column
	@NotBlank
	private String email;

	@Column
	@NotBlank
	@Size(min = 2, max = 15, message = "Numero de caracteres invalido")
	private String nombreusuario;

	@Column
	@NotBlank
	@Size(min = 59, max = 59, message = "Numero de caracteres invalido")
	private String password;

	@Transient
	@Size(min = 59, max = 59, message = "Numero de caracteres invalido")
	private String confirmacionPassword;

	public Usuario() {
		super();
	}

	public Usuario(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmacionPassword() {
		return confirmacionPassword;
	}

	public void setConfirmacionPassword(String confirmacionPassword) {
		this.confirmacionPassword = confirmacionPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((confirmacionPassword == null) ? 0 : confirmacionPassword.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreusuario == null) ? 0 : nombreusuario.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (confirmacionPassword == null) {
			if (other.confirmacionPassword != null)
				return false;
		} else if (!confirmacionPassword.equals(other.confirmacionPassword))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombreusuario == null) {
			if (other.nombreusuario != null)
				return false;
		} else if (!nombreusuario.equals(other.nombreusuario))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", nombreusuario=" + nombreusuario + ", password=" + password + ", confirmacionPassword="
				+ confirmacionPassword + "]";
	}

}
