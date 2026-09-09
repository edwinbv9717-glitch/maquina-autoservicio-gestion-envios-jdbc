package com.maquinaenvios.servlet;

import com.maquinaenvios.dao.HibernateEnvioDAO;
import com.maquinaenvios.modelo.Envio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Servlet encargado de mostrar y actualizar
 * la información de un envío.
 *
 * Utiliza Hibernate para la persistencia
 * de los datos.
 */
@WebServlet("/EditarEnvioServlet")
public class EditarEnvioServlet extends HttpServlet {

    // DAO Hibernate
    private final HibernateEnvioDAO envioDAO =
            new HibernateEnvioDAO();

    /**
     * GET:
     * Busca el envío y carga sus datos
     * en el formulario de edición.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro =
                request.getParameter("idEnvio");

        // Validar ID
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

            // Convertir ID
            long idEnvio =
                    Long.parseLong(idParametro);

            // Buscar mediante Hibernate
            Envio envio =
                    envioDAO.buscarPorId(idEnvio);

            // Validar resultado
            if (envio == null) {

                request.setAttribute(
                        "mensaje",
                        "No se encontró el envío con ID: "
                        + idEnvio
                );

                request.getRequestDispatcher(
                        "resultado.jsp"
                ).forward(request, response);

                return;
            }

            // Enviar envío al formulario JSP
            request.setAttribute(
                    "envio",
                    envio
            );

            request.getRequestDispatcher(
                    "editar-envio.jsp"
            ).forward(request, response);

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
                    "Error al cargar el envío: "
                    + e.getMessage()
            );

            request.getRequestDispatcher(
                    "resultado.jsp"
            ).forward(request, response);
        }
    }

    /**
     * POST:
     * Recibe los datos modificados desde el formulario
     * y actualiza el envío utilizando Hibernate.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Obtener ID
            long idEnvio =
                    Long.parseLong(
                            request.getParameter("idEnvio")
                    );

            // Obtener información del formulario
            String numeroGuia =
                    request.getParameter("numeroGuia");

            int idUsuario =
                    Integer.parseInt(
                            request.getParameter("idUsuario")
                    );

            int idRemitente =
                    Integer.parseInt(
                            request.getParameter("idRemitente")
                    );

            int idDestinatario =
                    Integer.parseInt(
                            request.getParameter("idDestinatario")
                    );

            int idCiudadOrigen =
                    Integer.parseInt(
                            request.getParameter("idCiudadOrigen")
                    );

            int idCiudadDestino =
                    Integer.parseInt(
                            request.getParameter("idCiudadDestino")
                    );

            int idServicio =
                    Integer.parseInt(
                            request.getParameter("idServicio")
                    );

            int idEstado =
                    Integer.parseInt(
                            request.getParameter("idEstado")
                    );

            BigDecimal valorTotal =
                    new BigDecimal(
                            request.getParameter("valorTotal")
                    );

            String observaciones =
                    request.getParameter("observaciones");

            // Crear objeto Envio
            Envio envio = new Envio();

            envio.setIdEnvio(idEnvio);
            envio.setNumeroGuia(numeroGuia);
            envio.setIdUsuario(idUsuario);
            envio.setIdRemitente(idRemitente);
            envio.setIdDestinatario(idDestinatario);
            envio.setIdCiudadOrigen(idCiudadOrigen);
            envio.setIdCiudadDestino(idCiudadDestino);
            envio.setIdServicio(idServicio);
            envio.setIdEstado(idEstado);
            envio.setValorTotal(valorTotal);
            envio.setObservaciones(observaciones);

            // Actualizar utilizando Hibernate
            boolean actualizado =
                    envioDAO.actualizar(envio);

            if (actualizado) {

                // Regresar al listado
                response.sendRedirect(
                        "ConsultaEnvioServlet"
                );

            } else {

                request.setAttribute(
                        "mensaje",
                        "No fue posible actualizar el envío."
                );

                request.getRequestDispatcher(
                        "resultado.jsp"
                ).forward(request, response);
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "mensaje",
                    "Uno de los valores numéricos no es válido."
            );

            request.getRequestDispatcher(
                    "resultado.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "mensaje",
                    "Error al actualizar: "
                    + e.getMessage()
            );

            request.getRequestDispatcher(
                    "resultado.jsp"
            ).forward(request, response);
        }
    }
}