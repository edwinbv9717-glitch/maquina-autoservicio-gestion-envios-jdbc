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

@WebServlet("/EnvioServlet")
public class EnvioServlet extends HttpServlet {

    private HibernateEnvioDAO envioDAO;

    @Override
    public void init() throws ServletException {
    envioDAO = new HibernateEnvioDAO();
        }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String numeroGuia =
                    request.getParameter("numeroGuia");

            int idUsuario =
                    Integer.parseInt(
                            request.getParameter("idUsuario"));

            int idRemitente =
                    Integer.parseInt(
                            request.getParameter("idRemitente"));

            int idDestinatario =
                    Integer.parseInt(
                            request.getParameter("idDestinatario"));

            int idCiudadOrigen =
                    Integer.parseInt(
                            request.getParameter("idCiudadOrigen"));

            int idCiudadDestino =
                    Integer.parseInt(
                            request.getParameter("idCiudadDestino"));

            int idServicio =
                    Integer.parseInt(
                            request.getParameter("idServicio"));

            int idEstado =
                    Integer.parseInt(
                            request.getParameter("idEstado"));

            BigDecimal valorTotal =
                    new BigDecimal(
                            request.getParameter("valorTotal"));

            String observaciones =
                    request.getParameter("observaciones");

            Envio envio = new Envio();

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

            boolean resultado =
                    envioDAO.insertar(envio);

            if (resultado) {

                response.sendRedirect(
                        "ConsultaEnvioServlet");

            } else {

                request.setAttribute(
                        "mensaje",
                        "No fue posible registrar el envío."
                );

                request.getRequestDispatcher(
                        "resultado.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "mensaje",
                    "Error: " + e.getMessage()
            );

            request.getRequestDispatcher(
                    "resultado.jsp")
                    .forward(request, response);
        }
    }
}