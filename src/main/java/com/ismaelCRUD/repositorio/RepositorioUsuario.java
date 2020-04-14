package com.ismaelCRUD.repositorio;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ismaelCRUD.modelo.Usuario;

//creamos esta interface extendiendo la clase crud repository que viene del spring data con esta clase evitamos hacer  las implementacionesde DAO
//para nuestras clases(en nuestro caso solo tenemos una pero me parece interesante explicar esta clase), solo le pasamos la clase de nuestro usuario y el tipo de dato

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {

	// metodo implementado de la clase crudReporitory que es como si nos devolviera
	// una consulta sql del nombre del usuario(este metodo lo utilizaremos luego en
	// el login de usuario)
	public Optional<Usuario> findBynombreusuario(String nombreusuario);
}
