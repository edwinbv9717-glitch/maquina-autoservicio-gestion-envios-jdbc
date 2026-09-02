package com.maquinaenvios.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/gestion_envios?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USUARIO = "root";

    private static final String PASSWORD = "Nicoll17*.17";

    public static Connection conectar() {

        try {
            // Cargar explícitamente el driver de MySQL para aplicaciones Web/Tomcat
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    PASSWORD
            );

            System.out.println("=================================");
            System.out.println("CONEXIÓN EXITOSA CON MYSQL");
            System.out.println("Base de datos: gestion_envios");
            System.out.println("=================================");

            return conexion;

        } catch (ClassNotFoundException e) {

            System.out.println("=================================");
            System.out.println("ERROR: NO SE ENCONTRÓ EL DRIVER JDBC DE MYSQL");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("=================================");

            e.printStackTrace();

            return null;

        } catch (SQLException e) {

            System.out.println("=================================");
            System.out.println("ERROR AL CONECTAR CON MYSQL");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("Código SQL: " + e.getErrorCode());
            System.out.println("Estado SQL: " + e.getSQLState());
            System.out.println("=================================");

            e.printStackTrace();

            return null;
        }
    }
}