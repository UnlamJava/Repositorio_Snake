package loggin;


import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class test {


	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		/*
		//Departamento dpto = new Departamento();
		
		dpto.setDescripcion("Finanzas");
				
		Persona persona = new Persona();

		persona.setDni(12345670);
		persona.setApellido("Sepulveda");
		persona.setNombre("Lolo");
		persona.setSexo('M');
		persona.setCodigo_depto(1);
*/
		Transaction tx = session.beginTransaction();
		try {
			//session.save(dpto);
			
			//session.saveOrUpdate(persona);
			//System.out.println("Se genero el registro con éxito.....!!");
			
			//tx.commit();
			
			//CriteriaBuilder cb = session.getCriteriaBuilder();
			//CriteriaQuery<Persona> cq = cb.createQuery(Persona.class);
			//cq.from(Persona.class);
			//Root<Persona> rp = cq.from(Persona.class);
			//cq.select(rp).where(cb.like(rp.get("nombre"), "%a%"));
			//List<Persona> lista = session.createQuery(cq).getResultList();
			//for(Persona p : lista) {
			//	System.out.println(p);
			//}			
			
			Query q;// = session.getNamedQuery("DniPersonas");
			/*
			System.out.println("\nSolo DNI de las Personas");
			
			
			List<Integer> listaConDni = q.getResultList();
			for(Integer i : listaConDni)
				System.out.println(i);
			*/
			System.out.println("\nOtra forma de consultar personas");
			//q = session.createQuery("Select p from Persona p");Select j from Jugador j"
			q = session.createQuery("Select j from Jugador j");
			//List<Persona> listaDePersonas = q.getResultList();
			//for(Persona p : listaDePersonas)
			List<Jugador> listaDePersonas = q.getResultList();
			for(Jugador p : listaDePersonas)
				System.out.println(p);
			
			
			Jugador j = new Jugador();
			j.setUsuario("Sherman11");
			j.setClave("12345611");
			
			
			System.out.println("se creo. "+j.toString());
			//session.saveOrUpdate(j);
			//tx.commit();
			
			
			System.out.println("\nListo otra ves los usuarios");
			//q = session.createQuery("Select p from Persona p");Select j from Jugador j"
			q = session.createQuery("Select j from Jugador j");
			//List<Persona> listaDePersonas = q.getResultList();
			//for(Persona p : listaDePersonas)
			 listaDePersonas = q.getResultList();
			for(Jugador p : listaDePersonas)
				System.out.println(p);
			
			long cantidad = (long) session.createQuery("select count(*) from Jugador j").uniqueResult();
			System.out.println("la cantidad de regsitros son: "+cantidad);
			/*
			System.out.println("\nSolo las personas de sexo femenino");
			q = session.createQuery("Select p from Persona p where p.sexo = 'F'");
			listaDePersonas = q.getResultList();
			for(Persona p : listaDePersonas)
				System.out.println(p);
			
			long totalPersonas = (long) session.createQuery("select count(*) from Persona p").uniqueResult();
			System.out.println("\nCantidad de personas: " + totalPersonas);
			
			System.out.println("\nPersonas y su Departamento");
			q = session.createQuery("Select p.dni, p.apellido, d.descripcion from Persona p, Departamento d where p.codigo_depto = d.codigo");
			List<Object[]> listaDeDatos = q.getResultList();
			for(Object[] registro : listaDeDatos)
				System.out.println(registro[0] + " " + registro[1] + " (" + registro[2] + ")");
			*/
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
