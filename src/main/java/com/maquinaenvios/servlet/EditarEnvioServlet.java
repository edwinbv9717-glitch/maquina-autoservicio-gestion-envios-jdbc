package com.maquinaenvios.servlet;

import com.maquinaenvios.dao.EnvioDAO;
import com.maquinaenvios.modelo.Envio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/EditarEnvioServlet")
public class EditarEnvioServlet extends HttpServlet {

    private final EnvioDAO envioDAO = new EnvioDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("idEnvio");

        if (idParametro == null || idParametro.isBlank()) {
            request.setAttribute(
                    "mensaje",
                    "No se recibió el ID del envío."
            );

            request.getRequestDispatcher("resultado.jsp").forward(request, response);
            return;
        }

        try {
            long idEnvio = Long.parseLong(idParametro);
            Envio envio = envioDAO.buscarPorId(idEnvio);

            if (envio == null) {
                request.setAttribute(
                        "mensaje",
                        "No se encontró el envío con ID: " + idEnvio
                );

                request.getRequestDispatcher("resultado.jsp").forward(request, response);
                return;
            }

            request.setAttribute("envio", envio);
            request.getRequestDispatcher("editar-envio.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute(
                    "mensaje",
                    "El ID del envío no es válido."
            );

            request.getRequestDispatcher("resultado.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long idEnvio = Long.parseLong(request.getParameter("idEnvio"));
            String numeroGuia = request.getParameter("numeroGuia");
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int idRemitente = Integer.parseInt(request.getParameter("idRemitente"));
            int idDestinatario = Integer.parseInt(request.getParameter("idDestinatario"));
            int idCiudadOrigen = Integer.parseInt(request.getParameter("idCiudadOrigen"));
            int idCiudadDestino = Integer.parseInt(request.getParameter("idCiudadDestino"));
            int idServicio = Integer.parseInt(request.getParameter("idServicio"));
            int idEstado = Integer.parseInt(request.getParameter("idEstado"));
            
            // CONVERSIÓN A BigDecimal para coincidir con EnvioDAO
            BigDecimal valorTotal = new BigDecimal(request.getParameter("valorTotal"));
            
            String observaciones = request.getParameter("observaciones");

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

            boolean actualizado = envioDAO.actualizar(envio);

            if (actualizado) {
                response.sendRedirect("ConsultaEnvioServlet");
            } else {
                request.setAttribute(
                        "mensaje",
                        "No fue posible actualizar el envío en la base de datos."
                );

                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza completa en la consola/Tomcat log para diagnóstico
            request.setAttribute(
                    "mensaje",
                    "Error al actualizar: " + e.getMessage()
            );

            request.getRequestDispatcher("resultado.jsp").forward(request, response);
        }
    }
}