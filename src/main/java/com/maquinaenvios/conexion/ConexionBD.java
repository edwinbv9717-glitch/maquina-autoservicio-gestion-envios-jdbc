package com.maquinaenvios.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Administra la conexión entre la aplicación y MySQL.
 *
 * Las credenciales se obtienen de variables de entorno
 * para evitar almacenar contraseñas en el código fuente.
 *
 * @author Edwin Bohórquez
 */
public class ConexionBD {

    // Dirección de la base de datos del proyecto.
    private static final String URL = obtenerVariable(
        "GESTION_DB_URL",
        "jdbc:mysql://localhost:3306/gestion_envios"
                + "?useSSL=false"
                + "&allowPublicKeyRetrieval=true"
                + "&serverTimezone=UTC"
);

    // Usuario de MySQL configurado en el equipo.
    private static final String USUARIO = obtenerVariable(
            "GESTION_DB_USER",
            "root"
    );

    /**
     * Obtiene una variable de entorno o utiliza el valor
     * predeterminado cuando está permitido.
     */
    private static String obtenerVariable(
            String nombre,
            String valorPredeterminado) {

        String valor = System.getenv(nombre);

        if (valor == null || valor.isBlank()) {
            return valorPredeterminado;
        }

        return valor;
    }

    /**
     * Establece una conexión con la base de datos.
     *
     * @return conexión activa con MySQL
     * @throws SQLException si faltan credenciales o falla
     * la conexión con la base de datos
     */
    public static Connection conectar() throws SQLException {

        // La contraseña no se almacena en el código fuente.
        String password = System.getenv("GESTION_DB_PASSWORD");

        if (password == null || password.isBlank()) {
            throw new SQLException(
                    "No está configurada la variable "
                    + "de entorno GESTION_DB_PASSWORD."
            );
        }

        try {
            // Carga el controlador JDBC de MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "No se encontró el controlador JDBC de MySQL.",
                    e
            );
        }

        // Abre y devuelve la conexión.
        return DriverManager.getConnection(
                URL,
                USUARIO,
                password
        );
    }
}