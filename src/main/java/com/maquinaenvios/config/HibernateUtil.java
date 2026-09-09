package com.maquinaenvios.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase encargada de crear y administrar la conexión
 * de Hibernate con la base de datos.
 *
 * @author Edwin
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {

        try {

            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");

            SESSION_FACTORY = configuration.buildSessionFactory();

        } catch (Throwable ex) {

            System.err.println(
                    "Error al crear SessionFactory: "
                    + ex.getMessage()
            );

            throw new ExceptionInInitializerError(ex);
        }
    }

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {

        return SESSION_FACTORY;
    }
}