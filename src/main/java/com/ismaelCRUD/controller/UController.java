package com.ismaelCRUD.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ismaelCRUD.modelo.Usuario;
import com.ismaelCRUD.servicio.ServicioUsuario;
/**
 * clase controladora que responde a eventos del usuario  e invoca peticiones al modelo
 * cuando se hace una solicitud de informacion (create, read, update, delete).
 * 
 * @author Ismael Heras Salvador
 *
 * @version 1.0 
 *
 * @since 04/05/2020
 */
@Controller
public class UController {

	@Autowired
	ServicioUsuario servicioUsuario;

	/**
	 * metodo que sirve para redirecccionar a la pagina principal de nuestra aplicacion
	 * mediante la solicitud get.
	 * @return retorna  a la pagina principal index.html.
	 */
	@GetMapping({ "/", "index" })
	public String index() {
		return "index";
	}
	

	/**
	 * metodo que nos redireeciona a la ruta del registro exterior del usuario
	 * mediante el metodo get.
	 * 
	 * @param modelo. objeto de la clase modelo para definir los campos que se veran
	 * en la pagina  registro
	 * 
	 * @return retorna la ruta de la pagina de nuestro registro exterior.
	 */
	@GetMapping("/registro")
	public String registro(Model modelo) {
		modelo.addAttribute("formulario", new Usuario());
		modelo.addAttribute("formTab", "active");
		return "formulario/registro";
	}

	/**
	 * metodo que nos redirecciona a la vista de usuario
	 * mediante el metodo get.
	 * 
	 * @param modelo. parametro para modelar lo que queremos que se ve aen la vista
	 * en este caso se vera la lista de usuarios.
	 * 
	 * @return retorna la ruta de la vista de nuestra aplicacion. 
	 */
	@GetMapping("/vista")
	public String getFormulario(Model modelo) {
		modelo.addAttribute("formulario", new Usuario());
		modelo.addAttribute("userList", servicioUsuario.getAllUsers());
		modelo.addAttribute("listTab", "active");
		return "formulario/vista";
	}
/**
 * metodo para recoger los datos que el usuario ha introducido en el formulario
 *  mediante el metodo post y luego mandarselo al servicio para que los guarde en la base de datos si todo es correcto.
 * 
 * @param usuario. objeto de nuestra clase usuario.
 * 
 * @param resultado. este objeto es el encargado de traernos la validacion del usuario.
 * 
 * @param modelo. objeto de la clase ModelMap para modelar la informacion que mostrara la ruta especificada
 * 
 * @return retorna la ruta formulario/registro de nuestra aplicacion.
 */
	@PostMapping("/registro")
	public String crearUsuario(@Valid @ModelAttribute("formulario") Usuario usuario, BindingResult resultado,
			ModelMap modelo) {
		modelo.addAttribute("formulario", usuario);
		modelo.addAttribute("registro", true);
		if (resultado.hasErrors()) {
			modelo.addAttribute("formulario", usuario);
			modelo.addAttribute("formTab", "active");
			return "formulario/registro";

		} else {
			try {
				servicioUsuario.creacionUsuario(usuario);
			} catch (Exception e) {
				modelo.addAttribute("mensajeError", e.getMessage());
				modelo.addAttribute("formulario", usuario);
				modelo.addAttribute("formTab", "active");
				return "formulario/registro";
			}
		}
		return "index";

	}
/**
 * metodo para mostrar por pantalla el formulario con los datos 
 * del usuario que queremos modificar.
 * 
 * @param modelo. objeto de la clase Model para modelar la informacion que mostrara la ruta especificada.
 * 
 * @param id. numero unico por el que indicamos al servicio que usuario es.
 * 
 * @return nos retorna a la vista de usuario, en cocreto a la lista de usuarios 
 * ya que se lo indicamos mediante el objeto model.
 * 
 * @throws Exception
 */
	@GetMapping("/editarUsuario/{id}")
	public String getEditarUsuario(Model modelo, @PathVariable(name = "id") Long id) throws Exception {
		Usuario usuarioParaEditar = servicioUsuario.getUsuarioById(id);

		modelo.addAttribute("formulario", usuarioParaEditar);
		modelo.addAttribute("userList", servicioUsuario.getAllUsers());
		modelo.addAttribute("formTab", "active");
		modelo.addAttribute("modoEditar", "true");
		return "formulario/vista";

	}
/**
 * metodo para modificar el usuario seleccionado por el metodo geteditarusuario
 * mediante el id unico del campo.
 * 
 * @param usuario. objeto de nuestra clase usuario
 * 
 * @param resultado. este objeto es el encargado de traernos la validacion del usuario.
 * 
 * @param modelo. objeto de la clase Model para modelar la informacion que mostrara la ruta especificada
 * 
 * @return retorna la ruta formulario/vista de nuestra aplicacion.
 */
	@PostMapping("/editarUsuario")
	public String postEditarUsuario(@Valid @ModelAttribute("formulario") Usuario usuario, BindingResult resultado,
			ModelMap modelo) {
		if (resultado.hasErrors()) {
			modelo.addAttribute("formulario", usuario);
			modelo.addAttribute("formTab", "active");
			modelo.addAttribute("modoEditar", "true");
		} else {
			try {
				servicioUsuario.actualizarUsuario(usuario);
				modelo.addAttribute("formulario", new Usuario());
				modelo.addAttribute("listTab", "active");
			} catch (Exception e) {
				modelo.addAttribute("mensajeError", e.getMessage());
				modelo.addAttribute("formulario", usuario);
				modelo.addAttribute("formTab", "active");
				modelo.addAttribute("userList", servicioUsuario.getAllUsers());
				modelo.addAttribute("modoEditar", "true");

			}
		}
		modelo.addAttribute("userList", servicioUsuario.getAllUsers());
		return "formulario/vista";

	}
/**
 * metodo para volver a la lista de usuarios cuando estamos en el modal de editar usuario.
 * 
 * @param modelo. objeto de la clase Model para modelar la informacion que mostrara la ruta especificada.
 * 
 * @return retorna la ruta vista de nuestra aplicacion.
 */
	@GetMapping("/editarUsuario/cancel")
	public String cancelarEditarUsuario(ModelMap modelo) {
		return "redirect:/vista";
	}
	
/**
 * metodo para selecionar un usuario por su id y mandarselo al servicio 
 * para que lo elimine.
 * 
 * @param modelo. objeto de la clase Model para modelar la informacion que mostrara la ruta especificada.
 * 
 * @param id. numero unico por el que indicamos al servicio que usuario es.
 * 
 * @return retorna la ruta vista de nuestra aplicacion.
 */
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUusario(Model modelo, @PathVariable(name = "id") Long id) {
		try {
			servicioUsuario.eliminarUusario(id);
		} catch (Exception e) {
			modelo.addAttribute("modalError", e.getMessage());
		}
		return "redirect:/vista";
		
	}

}









