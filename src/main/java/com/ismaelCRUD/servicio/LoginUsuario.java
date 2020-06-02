package com.ismaelCRUD.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import com.ismaelCRUD.repositorio.RepositorioUsuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * clase marcada con la anotacion servicio para poder realizar el login del usuario
 * implementa la interface UserDetailsService para poder hacerlo
 * 
 * @author Ismael Heras Salvador
 *
 * @version 1.0 
 *
 * @since 04/05/2020
 */

@Service
@Transactional
public class LoginUsuario implements UserDetailsService {

	// requerimos el repositorio del usuario (la interface que creamos con la
	// extension de la clase de spring CrudRepository).
	@Autowired
	RepositorioUsuario repositorioUsuario;

/**
 * metodo sobre escrito de la interface userdeatailsservice para buscar en la base de datos el nombre de usuario
 * y la contaseña para poder realizar el login.
 */
	@Override
	public UserDetails loadUserByUsername(String nombreusuario) throws UsernameNotFoundException {
		// guardamos en una variable el nombre del usuario de la base de datos cuando
		// esta cargada la sesion el login lo hace por atras spring
		// y lanzamos la excepcion por si el nombre de usuario no existe.
		com.ismaelCRUD.modelo.Usuario cargarSesionUsuario = repositorioUsuario.findBynombreusuario(nombreusuario)
				.orElseThrow(() -> new UsernameNotFoundException("El Login Del Usuario Es Invalido."));
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		// cargamos el usuario en la sesion gracias al objeto UserDetails de spring
		// (guardamos en la sesiion el nombre de usuario(que nos lo da el metodo
		// findByUsername)
		// y el password (al crear el objeto aplicacionusuario nos tare la password de
		// ese usuario))
		UserDetails usuario = (UserDetails) new User(nombreusuario, cargarSesionUsuario.getPassword(), roles);

		return usuario;

	}

}
