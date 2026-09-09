package com.maquinaenvios.dao;

import com.maquinaenvios.config.HibernateUtil;
import com.maquinaenvios.modelo.Envio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * DAO encargado de gestionar la entidad Envio
 * mediante el framework Hibernate ORM.
 *
 * Esta clase implementa las operaciones CRUD
 * utilizando sesiones y transacciones de Hibernate.
 */
public class HibernateEnvioDAO {

    /**
     * Registra un nuevo envío en la base de datos.
     *
     * @param envio objeto que contiene los datos del envío
     * @return true si el registro fue exitoso
     */
    public boolean insertar(Envio envio) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(envio);

            transaction.commit();

            System.out.println(
                    "Envío registrado correctamente con Hibernate."
            );

            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(
                    "Error al insertar envío con Hibernate: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

    /**
     * Consulta todos los envíos registrados.
     *
     * @return lista de objetos Envio
     */
    public List<Envio> listar() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session
                    .createQuery(
                            "FROM Envio ORDER BY idEnvio DESC",
                            Envio.class
                    )
                    .getResultList();

        } catch (Exception e) {

            System.err.println(
                    "Error al consultar envíos con Hibernate: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return List.of();
        }
    }

    /**
     * Busca un envío mediante su identificador.
     *
     * @param idEnvio identificador del envío
     * @return objeto Envio encontrado o null
     */
    public Envio buscarPorId(long idEnvio) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(
                    Envio.class,
                    idEnvio
            );

        } catch (Exception e) {

            System.err.println(
                    "Error al buscar envío con Hibernate: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return null;
        }
    }

    /**
     * Actualiza un envío existente.
     *
     * @param envio objeto con los datos actualizados
     * @return true si la actualización fue exitosa
     */
    public boolean actualizar(Envio envio) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(envio);

            transaction.commit();

            System.out.println(
                    "Envío actualizado correctamente con Hibernate."
            );

            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(
                    "Error al actualizar envío con Hibernate: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

    /**
     * Elimina un envío utilizando su identificador.
     *
     * @param idEnvio identificador del envío
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(long idEnvio) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Envio envio = session.get(
                    Envio.class,
                    idEnvio
            );

            if (envio == null) {

                transaction.rollback();

                System.out.println(
                        "No se encontró el envío con ID: "
                        + idEnvio
                );

                return false;
            }

            session.remove(envio);

            transaction.commit();

            System.out.println(
                    "Envío eliminado correctamente con Hibernate."
            );

            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(
                    "Error al eliminar envío con Hibernate: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }
}