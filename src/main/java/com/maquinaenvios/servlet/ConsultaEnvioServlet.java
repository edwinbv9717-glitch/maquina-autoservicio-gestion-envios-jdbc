package com.maquinaenvios.servlet;

import com.maquinaenvios.dao.HibernateEnvioDAO;
import com.maquinaenvios.modelo.Envio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet encargado de consultar y mostrar
 * todos los envíos registrados en el sistema.
 *
 * Utiliza HibernateEnvioDAO como capa de acceso
 * a los datos.
 */
@WebServlet("/ConsultaEnvioServlet")
public class ConsultaEnvioServlet extends HttpServlet {

    // DAO encargado de realizar las operaciones
    // de consulta mediante Hibernate.
    private final HibernateEnvioDAO envioDAO =
            new HibernateEnvioDAO();

    /**
     * Procesa las solicitudes GET utilizadas
     * para consultar los envíos.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Consulta todos los envíos mediante Hibernate.
            List<Envio> envios = envioDAO.listar();

            // Envía la lista de envíos al JSP.
            request.setAttribute(
                    "envios",
                    envios
            );

            // Redirige la información hacia la vista.
            request.getRequestDispatcher(
                    "listado-envios.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            // Muestra el error en la consola de NetBeans.
            e.printStackTrace();

            // Envía el mensaje de error al JSP.
            request.setAttribute(
                    "mensaje",
                    "Error al consultar los envíos: "
                    + e.getMessage()
            );

            request.getRequestDispatcher(
                    "resultado.jsp")
                    .forward(request, response);
        }
    }
}