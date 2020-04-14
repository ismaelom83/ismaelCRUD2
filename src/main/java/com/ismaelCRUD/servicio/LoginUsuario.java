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

//creamos una clase para el login de usuario que implementa la interface de spring security UserDetailsService por lo tanto
//tenemos que implementar el metodo loadUserByUsername que cargara el usuario desde la base de datos.

@Service
@Transactional
public class LoginUsuario implements UserDetailsService {

	// requerimos el repositorio del usuario (la interface que creamos con la
	// extension de la clase de spring CrudRepository).

	@Autowired
	RepositorioUsuario repositorioUsuario;

	// metodo implementado de la interface UserDetailsService que carga el nombre de
	// usuario de nuestra base de datos.
	@Override
	public UserDetails loadUserByUsername(String nombreusuario) throws UsernameNotFoundException {
		// guardamos en una variable el nombre del usuario de la base de datos cuando
		// esta cargada la sesion el login lo hace por atras spring
		// y lanzamos la excepcion por si el nombre de usuario no existe.
		com.ismaelCRUD.modelo.Usuario cargarSesionUsuario = repositorioUsuario.findBynombreusuario(nombreusuario)
				.orElseThrow(() -> new UsernameNotFoundException("El Login Del Usuario Es Invalido."));

		// mira maria esto lo tuve que hacer por que despues de muchas horas me di
		// cuenta de que spring security necesita para construir el usuario de mas abajo
		// un rol
		// y como no tenia roles (por que si no tengo que generar una clase nueva y
		// demas)demomento lo simulo y tira para delante en un futuro cuando acabe todo
		// intentare poner roles
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		// cargamos el usuario en la sesion gracias al objeto UserDetails de spring
		// (guardamos en la sesiion el nombre de usuario(que nos lo da el metodo
		// findByUsername)
		// y el password (al crear el objeto aplicacionusuario nos tare la password de
		// ese usuario))
		// aqui es donde me fallaba el login que me pedia que para generar el User (este
		// user es de la clase userdetails no de la mia) me pedia un rol
		// obligatoriamente
		UserDetails usuario = (UserDetails) new User(nombreusuario, cargarSesionUsuario.getPassword(), roles);

		return usuario;

	}

}
