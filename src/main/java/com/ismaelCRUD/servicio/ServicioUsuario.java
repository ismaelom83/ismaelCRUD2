package com.ismaelCRUD.servicio;

import com.ismaelCRUD.modelo.Usuario;
/**
 * interface servicio para crear los metodos de las operaciones del 
 * CRUD.
 * @author Ismael Heras Salvador
 *
 * @version 1.0 
 *
 * @since 04/05/2020
 */
public interface ServicioUsuario {
	/**
	 * metodo para traer todos los usuarios de la base de datos.
	 * 
	 * @return todos los usuarios de la base de datos. en una coleccion
	 */

	public Iterable<Usuario> getAllUsers();
/**
 * metodo para crear un usuario obteniendo los datos del controlador.
 * 
 * @param usuario. objeto de la clase usuario donde se guardaran los datos del usuario.
 * 
 * @return retorna un objeto usuario con todos los datos del mismo
 * 
 * @throws Exception
 */
	public Usuario creacionUsuario(Usuario usuario) throws Exception;
	/**
	 * metodo para obtener el id del usuario.
	 * 
	 * @param id. atributo de la clase Usuario.
	 * 
	 * @return retorna el numero unico (id) del usuario de nuestra base de dtos.
	 * 
	 * @throws Exception
	 */
	public 	Usuario getUsuarioById(Long id) throws Exception;
	/**
	 * metodo para actualizar los campos del los usuarios 
	 * en nuestra base de datos.
	 * 
	 * @param usuario. objeto de la clase usuario donde se guardaran los datos del usuario
	 * 
	 * @return devuelve un objeto de la clase usuario con todos sus campos modificados.
	 * 
	 * @throws Exception
	 */
	public Usuario actualizarUsuario(Usuario usuario) throws Exception;
	/**
	 * metodo para eliminar usuarios de nuestra base de datos.
	 * 
	 * @param id. atributo de la clase Usuario.
	 * 
	 * @throws Exception
	 */
	public void  eliminarUusario(Long id) throws Exception;
		
	
}
