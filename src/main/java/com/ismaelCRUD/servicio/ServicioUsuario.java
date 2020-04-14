package com.ismaelCRUD.servicio;

import com.ismaelCRUD.modelo.Usuario;

public interface ServicioUsuario {

	public Iterable<Usuario> getAllUsers();

	public Usuario creacionUsuario(Usuario usuario) throws Exception;
	
	public 	Usuario getUsuarioById(Long id) throws Exception;
	
	public Usuario actualizarUsuario(Usuario usuario) throws Exception;
	
	public void  eliminarUusario(Long id) throws Exception;
		
	
}
