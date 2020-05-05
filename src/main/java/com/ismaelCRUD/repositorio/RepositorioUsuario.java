package com.ismaelCRUD.repositorio;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ismaelCRUD.modelo.Usuario;


/**
 * interface repositorio que extendemos de la clase CrudRepository 
 * para extender sus metodos y realizar las consultas sql simuladas de esos metodos.
 * 
 * @author Ismael Heras Salvador
 *
 * @version 1.0 
 *
 * @since 04/05/2020
 */
@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {

/**
 * metodo de la clase CrudRepository, con el prefijo FindBy-- en nuestro caso
 *  nombreusuario(podia ser cualquier atributo de nuestra clase usuario) busca en la 
 *  base de datos por ese campo(este metodo simula una intruccion SQL select * from (dato a elegir)).
 *  
 * @param nombreusuario. nombre de usuario de nuestra base de datos.
 * 
 * @return retorna el nombre de usuario.
 */
	public Optional<Usuario> findBynombreusuario(String nombreusuario);
}
