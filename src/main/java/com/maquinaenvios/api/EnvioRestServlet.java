
package com.maquinaenvios.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maquinaenvios.dao.EnvioDAO;
import com.maquinaenvios.modelo.Envio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EnvioRestServlet", urlPatterns = {"/api/envios/*"})
public class EnvioRestServlet extends HttpServlet {

    private final EnvioDAO envioDAO = new EnvioDAO();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = obtenerId(request);

        try {
            if (id == null) {
                List<Envio> envios = envioDAO.listar();
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(), envios);
                return;
            }

            long idEnvio = Long.parseLong(id);

            if (idEnvio <= 0) {
                responderError(response,
                        HttpServletResponse.SC_BAD_REQUEST,
                        "El ID debe ser un número positivo.");
                return;
            }

            Envio envio = envioDAO.buscarPorId(idEnvio);

            if (envio == null) {
                responderError(response,
                        HttpServletResponse.SC_NOT_FOUND,
                        "No se encontró el envío solicitado.");
                return;
            }

            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), envio);

        } catch (NumberFormatException e) {
            responderError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El ID debe ser numérico.");
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Envio envio = mapper.readValue(
                    request.getInputStream(), Envio.class);

            if (!datosValidos(envio)) {
                responderError(response,
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Faltan datos obligatorios o hay valores inválidos.");
                return;
            }

            boolean creado = envioDAO.insertar(envio);

            if (creado) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                mapper.writeValue(response.getOutputStream(),
                        mensaje("Envío registrado correctamente."));
            } else {
                responderError(response,
                        HttpServletResponse.SC_CONFLICT,
                        "No fue posible registrar el envío. Verifique los datos y que el número de guía no exista.");
            }

        } catch (IOException e) {
            responderError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El cuerpo de la solicitud no contiene un JSON válido.");
        }
    }

    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = obtenerId(request);

        if (id == null) {
            responderError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Debe indicar el ID del envío en la URL.");
            return;
        }

        try {
            long idEnvio = Long.parseLong(id);

            if (idEnvio <= 0) {
                responderError(response,
                        HttpServletResponse.SC_BAD_REQUEST,
                        "El ID debe ser un número positivo.");
                return;
            }

            Envio existente = envioDAO.buscarPorId(idEnvio);

            if (existente == null) {
                responderError(response,
                        HttpServletResponse.SC_NOT_FOUND,
                        "No se encontró el envío que desea actualizar.");
                return;
            }

            Envio envio = mapper.readValue(
                    request.getInputStream(), Envio.class);

            if (!datosValidos(envio)) {
                responderError(response,
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Faltan datos obligatorios o hay valores inválidos.");
                return;
            }

            envio.setIdEnvio(idEnvio);

            if (envioDAO.actualizar(envio)) {
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(),
                        mensaje("Envío actualizado correctamente."));
            } else {
                responderError(response,
                        HttpServletResponse.SC_CONFLICT,
                        "No fue posible actualizar el envío.");
            }

        } catch (NumberFormatException e) {
            responderError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El ID debe ser numérico.");
        } catch (IOException e) {
            responderError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El cuerpo de la solicitud no contiene un JSON válido.");
        }
    }

    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = obtenerId(request);

        if (id == null) {
            responderError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Debe indicar el ID del envío en la URL.");
            return;
        }

        try {
            long idEnvio = Long.parseLong(id);

            if (idEnvio <= 0) {
                responderError(response,
                        HttpServletResponse.SC_BAD_REQUEST,
                        "El ID debe ser un número positivo.");
                return;
            }

            if (envioDAO.eliminar(idEnvio)) {
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(),
                        mensaje("Envío eliminado correctamente."));
            } else {
                responderError(response,
                        HttpServletResponse.SC_NOT_FOUND,
                        "No se encontró el envío o no fue posible eliminarlo.");
            }

        } catch (NumberFormatException e) {
            responderError(response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El ID debe ser numérico.");
        }
    }

    private String obtenerId(HttpServletRequest request) {
        String ruta = request.getPathInfo();

        if (ruta == null || ruta.equals("/")) {
            return null;
        }

        String id = ruta.substring(1);

        if (id.isBlank() || id.contains("/")) {
            return null;
        }

        return id;
    }

    private boolean datosValidos(Envio envio) {
        return envio != null
                && envio.getNumeroGuia() != null
                && !envio.getNumeroGuia().isBlank()
                && envio.getIdUsuario() > 0
                && envio.getIdRemitente() > 0
                && envio.getIdDestinatario() > 0
                && envio.getIdCiudadOrigen() > 0
                && envio.getIdCiudadDestino() > 0
                && envio.getIdServicio() > 0
                && envio.getIdEstado() > 0
                && envio.getValorTotal() != null
                && envio.getValorTotal().signum() >= 0
                && envio.getObservaciones() != null
                && envio.getObservaciones().length() <= 300;
    }

    private void responderError(
            HttpServletResponse response,
            int estado,
            String detalle) throws IOException {

        response.setStatus(estado);
        mapper.writeValue(response.getOutputStream(),
                mensaje(detalle));
    }

    private Mensaje mensaje(String detalle) {
        return new Mensaje(detalle);
    }

    public static class Mensaje {

        private String mensaje;

        public Mensaje() {
        }

        public Mensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
