package com.maquinaenvios.principal;

import com.maquinaenvios.conexion.ConexionBD;
import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        Connection conexion = ConexionBD.conectar();

        if (conexion != null) {
            System.out.println("La conexión funciona correctamente.");

            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión.");
            }

        } else {
            System.out.println("No fue posible conectar con MySQL.");
        }
    }
}