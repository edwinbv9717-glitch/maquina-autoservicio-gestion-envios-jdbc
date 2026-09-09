package com.maquinaenvios.servlet;

import com.maquinaenvios.dao.HibernateEnvioDAO;
import com.maquinaenvios.modelo.Envio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet encargado de buscar un envío
 * mediante su identificador.
 *
 * Utiliza Hibernate como framework de persistencia.
 */
@WebServlet("/BuscarEnvioServlet")
public class BuscarEnvioServlet extends HttpServlet {

    // DAO Hibernate
    private final HibernateEnvioDAO envioDAO =
            new HibernateEnvioDAO();

    /**
     * Método GET utilizado para realizar
     * la búsqueda del envío.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el ID enviado desde el formulario
        String idParametro =
                request.getParameter("idEnvio");

        // Validar que el usuario haya ingresado un ID
        if (idParametro == null || idParametro.isBlank()) {

            request.setAttribute(
                    "mensaje",
                    "Debe ingresar el ID del envío."
            );

            request.getRequestDispatcher(
                    "buscar-envio.jsp"
            ).forward(request, response);

            return;
        }

        try {

            // Convertir el ID recibido a número
            long idEnvio =
                    Long.parseLong(idParametro);

            // Buscar el envío mediante Hibernate
            Envio envio =
                    envioDAO.buscarPorId(idEnvio);

            // Verificar si se encontró
            if (envio != null) {

                request.setAttribute(
                        "envio",
                        envio
                );

            } else {

                request.setAttribute(
                        "mensaje",
                        "No se encontró ningún envío con el ID: "
                        + idEnvio
                );
            }

            // Regresar al JSP
            request.getRequestDispatcher(
                    "buscar-envio.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "mensaje",
                    "El ID del envío debe ser un número válido."
            );

            request.getRequestDispatcher(
                    "buscar-envio.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "mensaje",
                    "Error al buscar el envío: "
                    + e.getMessage()
            );

            request.getRequestDispatcher(
                    "resultado.jsp"
            ).forward(request, response);
        }
    }
}