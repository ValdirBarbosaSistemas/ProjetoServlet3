package br.com.acme.agenda.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.acme.agenda.model.Usuario;

public class JPAUtil {

	private static EntityManagerFactory factory;
	
	public static Usuario usuario;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}
	
	public static void finalFactory() {
		if(factory != null) {
			factory.close();
		}
	}
}
