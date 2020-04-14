package com.ismaelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//clase que utilizamos para sobrescribir los ajustes que vienen por defecto en spring security.
@Configuration
@EnableWebSecurity
public class SeguridadWeb extends WebSecurityConfigurerAdapter {

	// areglo donde nombramos las carpetas que en el siguiente metodo le damos
	// permiso para entrar si no no nos cargaria el js nilos css ni las imagenes
	String[] resources = new String[] { "/css/**", "/img/**", "/js/**", "/DOC/**" };

	// metodo que se sobrescribe de la clase extendida de spring y modificamos los
	// parametros de seguridad como queramos
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// cualquiera que tenga la ruta resources tiene aceso
				.antMatchers(resources).permitAll()
				// cualquiera tiene acceso al index principal
				.antMatchers("/", "/index", "/registro").permitAll()
				// cualquier request necesitara autentificacion
				.anyRequest().authenticated().and().formLogin()
				// esta es la pagina que utilizamos en el login que esta permitida la entrada a
				// todo el mundo.
				.loginPage("/index").permitAll()
				// nos redirige ala lista al logearnos con exito
				.defaultSuccessUrl("/vista")
				// si falla el login nos muestra error
				.failureUrl("/index?error=verdad")
				// estos parametros se corrsponden a los campos name de nuestro formulario de
				// login
				.usernameParameter("nombreusuario").passwordParameter("password")
				// y con esta ultima instruccion le decimos que cuando pulsemos el logout nos
				// cierre la sesion y nos redigira al login
				.and().csrf().disable().logout().permitAll().logoutSuccessUrl("/index?logout");
	}

	// clase de spring security para descifrar o cifrar la contraseña (de momento la
	// he cifrado desde su pagina web
	// https://www.dailycred.com/article/bcrypt-calculator
	// mas adelante habra que incorporarlo en la aplicacion)
	BCryptPasswordEncoder passwordDescifrada;

	// este es el metodo de la clase para decodificar la contraseña.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// el numero 4 indica la longitud de la contraseña.
		passwordDescifrada = new BCryptPasswordEncoder(4);
		return passwordDescifrada;
	}

	// acemos uso de la interface userdetailsservice que se utiliza para cargar los
	// datos del usuario y implementa el metodoAuthenticationManagerBuilder
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Especifica el encargado del login y desencriptacion del password
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
