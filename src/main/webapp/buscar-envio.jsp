<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.maquinaenvios.modelo.Envio"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Envío</title>
</head>

<body>

    <h1>Buscar Envío</h1>

    <form action="BuscarEnvioServlet" method="get">

        <label for="idEnvio">
            ID del envío:
        </label>

        <input
            type="number"
            id="idEnvio"
            name="idEnvio"
            required
        >

        <button type="submit">
            Buscar
        </button>

    </form>

    <hr>

    <%
        String mensaje =
                (String) request.getAttribute("mensaje");

        Envio envio =
                (Envio) request.getAttribute("envio");

        if (mensaje != null) {
    %>

        <p>
            <strong><%= mensaje %></strong>
        </p>

    <%
        }

        if (envio != null) {
    %>

        <h2>Información del envío</h2>

        <p>
            <strong>ID:</strong>
            <%= envio.getIdEnvio() %>
        </p>

        <p>
            <strong>Número de guía:</strong>
            <%= envio.getNumeroGuia() %>
        </p>

        <p>
            <strong>ID Usuario:</strong>
            <%= envio.getIdUsuario() %>
        </p>

        <p>
            <strong>ID Remitente:</strong>
            <%= envio.getIdRemitente() %>
        </p>

        <p>
            <strong>ID Destinatario:</strong>
            <%= envio.getIdDestinatario() %>
        </p>

        <p>
            <strong>Ciudad origen:</strong>
            <%= envio.getIdCiudadOrigen() %>
        </p>

        <p>
            <strong>Ciudad destino:</strong>
            <%= envio.getIdCiudadDestino() %>
        </p>

        <p>
            <strong>Servicio:</strong>
            <%= envio.getIdServicio() %>
        </p>

        <p>
            <strong>Estado:</strong>
            <%= envio.getIdEstado() %>
        </p>

        <p>
            <strong>Fecha creación:</strong>
            <%= envio.getFechaCreacion() %>
        </p>

        <p>
            <strong>Valor total:</strong>
            <%= envio.getValorTotal() %>
        </p>

        <p>
            <strong>Observaciones:</strong>
            <%= envio.getObservaciones() %>
        </p>

    <%
        }
    %>

    <br>

    <a href="index.jsp">
        Volver al inicio
    </a>

    <br><br>

    <a href="ConsultaEnvioServlet">
        Ver todos los envíos
    </a>

</body>
</html>