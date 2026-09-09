package com.maquinaenvios.servlet;

import com.maquinaenvios.dao.HibernateEnvioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet encargado de eliminar un envío.
 *
 * Utiliza Hibernate como framework de persistencia.
 */
@WebServlet("/EliminarEnvioServlet")
public class EliminarEnvioServlet extends HttpServlet {

    // DAO Hibernate
    private final HibernateEnvioDAO envioDAO =
            new HibernateEnvioDAO();

    /**
     * POST:
     * Recibe el ID del envío y realiza
     * la eliminación mediante Hibernate.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro =
                request.getParameter("idEnvio");

        // Validar que se haya recibido el ID
        if (idParametro == null || idParametro.isBlank()) {

            request.setAttribute(
                    "mensaje",
                    "No se recibió el ID del envío."
            );

            request.getRequestDispatcher(
                    "resultado.jsp"
            ).forward(request, response);

            return;
        }

        try {

            // Convertir el ID
            long idEnvio =
                    Long.parseLong(idParametro);

            // Eliminar mediante Hibernate
            boolean eliminado =
                    envioDAO.eliminar(idEnvio);

            if (eliminado) {

                // Regresar al listado
                response.sendRedirect(
                        "ConsultaEnvioServlet"
                );

            } else {

                request.setAttribute(
                        "mensaje",
                        "No fue posible eliminar el envío."
                );

                request.getRequestDispatcher(
                        "resultado.jsp"
                ).forward(request, response);
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "mensaje",
                    "El ID del envío no es válido."
            );

            request.getRequestDispatcher(
                    "resultado.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "mensaje",
                    "Error al eliminar el envío: "
                    + e.getMessage()
            );

            request.getRequestDispatcher(
                    "resultado.jsp"
            ).forward(request, response);
        }
    }
}