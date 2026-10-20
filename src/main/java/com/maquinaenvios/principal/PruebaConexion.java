
package com.maquinaenvios.principal;

import com.maquinaenvios.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase de prueba para verificar la conexión con MySQL.
 *
 * @author Edwin Bohórquez
 */
public class PruebaConexion {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("       PRUEBA DE CONEXION");
        System.out.println("==================================");

        // Intenta establecer la conexión con MySQL.
        try (Connection conexion = ConexionBD.conectar()) {

            if (conexion.isValid(5)) {
                System.out.println(
                        "Conexion exitosa con MySQL."
                );
            } else {
                System.out.println(
                        "La conexion no es valida."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "No fue posible conectar con MySQL."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );
        }

        System.out.println("==================================");
        System.out.println("       PRUEBA FINALIZADA");
        System.out.println("==================================");
    }
}
