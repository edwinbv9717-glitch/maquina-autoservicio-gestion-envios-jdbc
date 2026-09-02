package com.maquinaenvios.servlet;

import com.maquinaenvios.dao.EnvioDAO;
import com.maquinaenvios.modelo.Envio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ConsultaEnvioServlet")
public class ConsultaEnvioServlet extends HttpServlet {

    private EnvioDAO envioDAO;

    @Override
    public void init() throws ServletException {
        envioDAO = new EnvioDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Envio> envios =
                    envioDAO.listar();

            request.setAttribute(
                    "envios",
                    envios
            );

            request.getRequestDispatcher(
                    "listado-envios.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

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