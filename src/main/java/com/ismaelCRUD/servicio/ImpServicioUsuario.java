package com.ismaelCRUD.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.ismaelCRUD.modelo.Usuario;
import com.ismaelCRUD.repositorio.RepositorioUsuario;

@Service
public class ImpServicioUsuario implements ServicioUsuario {

	@Autowired
	BCryptPasswordEncoder encriptacion;

	@Autowired
	RepositorioUsuario repositorio;

	@Override
	public Iterable<Usuario> getAllUsers() {

		return repositorio.findAll();
	}

	private boolean comprobarExisteNombreUsuario(Usuario usuario) throws Exception {
		Optional<Usuario> creacionUsuario = repositorio.findBynombreusuario(usuario.getNombreusuario());
		if (creacionUsuario.isPresent()) {
			throw new Exception("Este nombre de usuario ya existe");
		}
		return true;
	}

	private boolean comprobacionPasswordValida(Usuario usuario) throws Exception {
		if (!usuario.getPassword().equals(usuario.getConfirmacionPassword())) {
			throw new Exception("Las contraseñas no coinciden");
		}
		return true;

	}

	@Override
	public Usuario creacionUsuario(Usuario usuario) throws Exception {
		if (comprobarExisteNombreUsuario(usuario) && comprobacionPasswordValida(usuario)) {
			String encriptarPassword = encriptacion.encode(usuario.getPassword());
			usuario.setPassword(encriptarPassword);
			usuario = repositorio.save(usuario);
		}
		return usuario;
	}

	
	@Override
	public Usuario getUsuarioById(Long id) throws Exception {
		
		return repositorio.findById(id).orElseThrow(() -> new Exception("este usuario no existe"));
	}

	@Override
	public Usuario actualizarUsuario(Usuario fromUsuario) throws Exception {
		Usuario toUsuario = getUsuarioById(fromUsuario.getId());
		mapeoUsuario(fromUsuario, toUsuario);
		return repositorio.save(toUsuario);
	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 */
	
	protected void mapeoUsuario(Usuario from,Usuario to) {
		to.setNombreusuario(from.getNombreusuario());
		to.setNombre(from.getNombre());
		to.setApellidos(from.getApellidos());
		to.setEmail(from.getEmail());

	}

	@Override
	public void eliminarUusario(Long id) throws Exception {
		Usuario usuario = getUsuarioById(id);
		
		repositorio.delete(usuario);
	}
}








