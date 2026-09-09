package com.maquinaenvios.principal;

import com.maquinaenvios.dao.HibernateEnvioDAO;
import com.maquinaenvios.modelo.Envio;
import java.util.List;

/**
 * Clase utilizada para comprobar la integración
 * entre Hibernate ORM y la base de datos MySQL.
 */
public class PruebaHibernate {

    public static void main(String[] args) {

        System.out.println(
                "============================================"
        );

        System.out.println(
                "       PRUEBA DEL FRAMEWORK HIBERNATE"
        );

        System.out.println(
                "============================================"
        );

        HibernateEnvioDAO envioDAO =
                new HibernateEnvioDAO();

        System.out.println();

        System.out.println(
                "Consultando envíos mediante Hibernate..."
        );

        List<Envio> envios =
                envioDAO.listar();

        System.out.println();

        System.out.println(
                "Cantidad de envíos encontrados: "
                + envios.size()
        );

        System.out.println();

        for (Envio envio : envios) {

            System.out.println(
                    "ID: "
                    + envio.getIdEnvio()
                    + " | Guía: "
                    + envio.getNumeroGuia()
                    + " | Valor: "
                    + envio.getValorTotal()
            );
        }

        System.out.println();

        System.out.println(
                "============================================"
        );

        System.out.println(
                "       PRUEBA HIBERNATE FINALIZADA"
        );

        System.out.println(
                "============================================"
        );
    }
}