package menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import loggin.Jugador;

public class DatosUsuarios {

	public DatosUsuarios() throws IOException {
	}

	@SuppressWarnings("unchecked")
	public boolean setCrearUsuario(Jugador j1) {

		Configuration cfg;
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean respuesta = false;
		try {
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			factory = cfg.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			String query = "Select count(*) from Jugador j where j.usuario like '" + j1.getUsuario() + "' and "
					+ "j.clave like '" + j1.getClave() + "'";

			long cantidad = (long) session.createQuery(query).uniqueResult();

			if (cantidad > 0)
				respuesta = false;
			else {
				Jugador j = new Jugador();
				j.setUsuario(j1.getUsuario());
				j.setClave(j1.getClave());

				session.saveOrUpdate(j);
				tx.commit();
				respuesta = true;
			}

		} catch (

		HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();

			return respuesta;

		}
	}

	@SuppressWarnings("unchecked")
	public boolean getUsuario(Jugador j1) {
		boolean respuesta = false;
		Configuration cfg;
		SessionFactory factory = null;
		Session session = null;
		try {
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			factory = cfg.buildSessionFactory();
			session = factory.openSession();

			String query = "Select count(*) from Jugador j where j.usuario like '" + j1.getUsuario() + "' and "
					+ "j.clave like '" + j1.getClave() + "'";

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

	/*
	 * TEST DE LA CLASE
	 */

	public static void main(String[] args) throws IOException {
		DatosUsuarios baseDeDatos = new DatosUsuarios();

		Jugador loco = new Jugador();
		loco.setUsuario("micol");
		loco.setClave("Rios115");

		if (baseDeDatos.setCrearUsuario(loco))
			System.out.println("Se encontro dato");
		else
			System.out.println("NO se encontro dato");

	}

}
