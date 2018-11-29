package login;

import java.io.IOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UsuariosDB {

	private static final String hibernateCfg = "hibernate.cfg.xml";
	private Configuration cfg;
	private SessionFactory factory = null;
	private Session session = null;
	private Transaction tx = null;

	public UsuariosDB(){}

	@SuppressWarnings("unchecked")
	public boolean setCrearUsuario(Jugador j1) {

		
		boolean respuesta = false;
		
		try {
			cargarConfiguracion();
			
			tx = session.beginTransaction();

			String query = geQuery(j1);

			long cantidad = (long) session.createQuery(query).uniqueResult();

			if (cantidad > 0)
				
				respuesta = false;
			
			else{
				
				Jugador j = new Jugador();
				
				j.setUsuario(j1.getUsuario());
				
				j.setClave(j1.getClave());

				session.save(j);
				
				tx.commit();
				
				respuesta = true;
			}

		} catch (HibernateException e) {
			
			if (tx != null)
				
				tx.rollback();
			
			//e.printStackTrace();
			
		} finally {
			
			session.close();
			
			factory.close();

			return respuesta;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean getUsuario(Jugador j1) {
		boolean respuesta = false;		
		try {
			cargarConfiguracion();

			String query = geQuery(j1);

			long cantidad = (long) session.createQuery(query).uniqueResult();

			if (cantidad > 0)
				
				respuesta = true;
			else
				respuesta = false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			session.close();
			
			factory.close();
			
			return respuesta;

		}

	}
	
	public void cargarConfiguracion() {
		cfg = new Configuration();
		cfg.configure(hibernateCfg);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public String geQuery(Jugador j1) {
		
		String query = "Select count(*) from Jugador j where j.usuario like '" + j1.getUsuario() + "' and "
			     + "j.clave like '" + j1.getClave() + "'";
		
		return query;
	}

}
