package com.maquinaenvios.servlet;

import com.maquinaenvios.dao.EnvioDAO;
import com.maquinaenvios.modelo.Envio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/BuscarEnvioServlet")
public class BuscarEnvioServlet extends HttpServlet {

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
                    "Debe ingresar el ID del envío."
            );

            request.getRequestDispatcher(
                    "buscar-envio.jsp"
            ).forward(request, response);

            return;
        }

        try {

            long idEnvio = Long.parseLong(idParametro);

            Envio envio = envioDAO.buscarPorId(idEnvio);

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
        }
    }
}