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
/**
 * clase que utilizamos poara el modelo de datos de nuestra base de datos
 * al haber marcado esta clase con la anotacion entity automaticamente se combierte en
 * una tabla de nuestra base de datos.
 * 
 * @author Ismael Heras Salvador
 *
 * @version 1.0 
 *
 * @since 04/05/2020
 */
@Entity
public class Usuario implements Serializable {

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
	private String password;

	@Transient
	private String confirmacionPassword;

	
	private String imagen;
	
	public Usuario() {
		super();
	}

	/**
	 * constructor de la clase Usuario.
	 * 
	 * @param id. numero unico de usuario en la base de datos.
	 */
	public Usuario(long id) {
		super();
		this.id = id;
	}
	
	
	
public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

/**
 * 
 * @return numero unico de usuario en la base de datos
 */
	public long getId() {
		return id;
	}
/**
 * 
 * @param id. numero unico de usuario en la base de datos
 */
	public void setId(long id) {
		this.id = id;
	}
/**
 * 
 * @return nombre del usuario de la base de datos.
 */
	public String getNombre() {
		return nombre;
	}
/**
 * 
 * @param nombre. nombre del usuario de la base de datos.
 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
/**
 * 
 * @return apellidos del usuario de la base de datos.
 */
	public String getApellidos() {
		return apellidos;
	}
/**
 * 
 * @param apellidos. apellidos del usuario de la base de datos.
 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
/**
 * 
 * @return email del usuario de la base de datos.
 */
	public String getEmail() {
		return email;
	}
/**
 * 
 * @param email. email del usuario de la base de datos.
 */
	public void setEmail(String email) {
		this.email = email;
	}
/**
 * 
 * @return nombreusuario del usuario de la base de datos.
 */
	public String getNombreusuario() {
		return nombreusuario;
	}
/**
 * 
 * @param nombreusuario. nombreusuario del usuario de la base de datos.
 */
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
/**
 * 
 * @return password del usuario de la base de datos.
 */
	public String getPassword() {
		return password;
	}
/**
 * 
 * @param password. password del usuario de la base de datos.
 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return confirma que las password introducidas son iguales
	 */
	public String getConfirmacionPassword() {
		return confirmacionPassword;
	}
/**
 * 
 * @param confirmacionPassword. segunda password para verificar 
 * que las password son iguales.
 */
	public void setConfirmacionPassword(String confirmacionPassword) {
		this.confirmacionPassword = confirmacionPassword;
	}
/**
 * metodo sobreescrito de la clase object que complementa al metodo equals
 * para comparar objetos.
 */

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
	result = prime * result + ((confirmacionPassword == null) ? 0 : confirmacionPassword.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
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
	if (imagen == null) {
		if (other.imagen != null)
			return false;
	} else if (!imagen.equals(other.imagen))
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
	return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
			+ ", nombreusuario=" + nombreusuario + ", password=" + password + ", confirmacionPassword="
			+ confirmacionPassword + ", imagen=" + imagen + "]";
}


}
