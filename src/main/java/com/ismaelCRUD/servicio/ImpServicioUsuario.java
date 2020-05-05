package com.ismaelCRUD.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.ismaelCRUD.modelo.Usuario;
import com.ismaelCRUD.repositorio.RepositorioUsuario;
/**
 * clase servicio que implementa la interface Servicio usuario 
 * que contiene los metodos para la soperaciones del CRUD.
 * 
 * @author Ismael Heras Salvador
 *
 * @version 1.0 
 *
 * @since 04/05/2020
 */
@Service
public class ImpServicioUsuario implements ServicioUsuario {

	@Autowired
	BCryptPasswordEncoder encriptacion;

	@Autowired
	RepositorioUsuario repositorio;
/**
 * metodo implementado de la interface ServicioUsuario para traer todos los usuarios de la base de datos
 * en una coleccion.
 */
	@Override
	public Iterable<Usuario> getAllUsers() {

		return repositorio.findAll();
	}
/**
 * metodo para comprobar que el nombre de usuario que vamos a introducir
 * en un nuevo registro no existe.
 * 
 * @param usuario. objeto de la clase usuario que contiene todos los datos del usuario.
 * 
 * @return true si no existe el nombre de usuario
 * 
 * @throws Exception
 */
	private boolean comprobarExisteNombreUsuario(Usuario usuario) throws Exception {
		Optional<Usuario> creacionUsuario = repositorio.findBynombreusuario(usuario.getNombreusuario());
		if (creacionUsuario.isPresent()) {
			throw new Exception("Este nombre de usuario ya existe");
		}
		return true;
	}
/**
 * metodo para comprobar que en el formulario de registro las contraseñas 
 * introducidas son iguales.
 * 
 * @param usuario. objeto de la clase usuario que contiene todos los datos del usuario.
 * 
 * @return true si las contraseñas coinciden.
 * 
 * @throws Exception
 */
	private boolean comprobacionPasswordValida(Usuario usuario) throws Exception {
		if (!usuario.getPassword().equals(usuario.getConfirmacionPassword())) {
			throw new Exception("Las contraseñas no coinciden");
		}
		return true;

	}
	/**
	 * metodo implementado de la interface ServicioUsuario para 
	 * crear usuarios nuevos en nuestra base de datos, comprueba que el
	 * nombre de usuario no existe ya en la base de datos, que las password 
	 * coinciden, encrita el password y guarda todos los datos del usuario en 
	 * la base de datos.
	 */
	@Override
	public Usuario creacionUsuario(Usuario usuario) throws Exception {
		if (comprobarExisteNombreUsuario(usuario) && comprobacionPasswordValida(usuario)) {
			String encriptarPassword = encriptacion.encode(usuario.getPassword());
			usuario.setPassword(encriptarPassword);
			usuario = repositorio.save(usuario);
		}
		return usuario;
	}
	/**
	 * metodo implementado de la interface ServicioUsuario para seleccionar el id 
	 * del usuario que queremos modificar o borrar mediante el metodo get y si no lo encuentra lanza una excepcion..
	 */
	@Override
	public Usuario getUsuarioById(Long id) throws Exception {
		
		return repositorio.findById(id).orElseThrow(() -> new Exception("este usuario no existe"));
	}

	/**
	 * metodo implementado de la interface ServicioUsuario para 
	 * actualizar los datos del usuario expecificado por el id luego
	 * lo mapea y lo guarda en la base de datos modificado.
	 */
	@Override
	public Usuario actualizarUsuario(Usuario fromUsuario) throws Exception {
		Usuario toUsuario = getUsuarioById(fromUsuario.getId());
		mapeoUsuario(fromUsuario, toUsuario);
		return repositorio.save(toUsuario);
	}
	
	/**
	 * metodo para mapear el usuario con los datos del formulario
	 * y pasarlos al usuario d la base de datos.
	 * 
	 * @param from. datos del usuario en el formulario de update
	 * 
	 * @param to los datos del usuario actualizados para guardar en la base de datos.
	 */
	
	protected void mapeoUsuario(Usuario from,Usuario to) {
		to.setNombreusuario(from.getNombreusuario());
		to.setNombre(from.getNombre());
		to.setApellidos(from.getApellidos());
		to.setEmail(from.getEmail());

	}
	/**
	 * metodo implementado de la interface ServicioUsuario para elimminar usuarios
	 * el metodo getUsuarioById nos trae el id del usuario que queremos borrar.
	 */
	@Override
	public void eliminarUusario(Long id) throws Exception {
		Usuario usuario = getUsuarioById(id);
		
		repositorio.delete(usuario);
	}
}








