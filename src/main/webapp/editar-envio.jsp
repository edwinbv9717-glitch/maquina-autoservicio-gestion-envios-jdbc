<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.maquinaenvios.modelo.Envio"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Editar Envío</title>

</head>

<body>

<h1>Editar Envío</h1>

<%
    Envio envio =
        (Envio) request.getAttribute("envio");

    if (envio != null) {
%>

<form action="EditarEnvioServlet" method="post">

    <input
        type="hidden"
        name="idEnvio"
        value="<%= envio.getIdEnvio() %>"
    >

    <p>
        <label>Número de guía:</label>
        <br>

        <input
            type="text"
            name="numeroGuia"
            value="<%= envio.getNumeroGuia() %>"
            required
        >
    </p>

    <p>
        <label>ID Usuario:</label>
        <br>

        <input
            type="number"
            name="idUsuario"
            value="<%= envio.getIdUsuario() %>"
            required
        >
    </p>

    <p>
        <label>ID Remitente:</label>
        <br>

        <input
            type="number"
            name="idRemitente"
            value="<%= envio.getIdRemitente() %>"
            required
        >
    </p>

    <p>
        <label>ID Destinatario:</label>
        <br>

        <input
            type="number"
            name="idDestinatario"
            value="<%= envio.getIdDestinatario() %>"
            required
        >
    </p>

    <p>
        <label>ID Ciudad Origen:</label>
        <br>

        <input
            type="number"
            name="idCiudadOrigen"
            value="<%= envio.getIdCiudadOrigen() %>"
            required
        >
    </p>

    <p>
        <label>ID Ciudad Destino:</label>
        <br>

        <input
            type="number"
            name="idCiudadDestino"
            value="<%= envio.getIdCiudadDestino() %>"
            required
        >
    </p>

    <p>
        <label>ID Servicio:</label>
        <br>

        <input
            type="number"
            name="idServicio"
            value="<%= envio.getIdServicio() %>"
            required
        >
    </p>

    <p>
        <label>ID Estado:</label>
        <br>

        <input
            type="number"
            name="idEstado"
            value="<%= envio.getIdEstado() %>"
            required
        >
    </p>

    <p>
        <label>Fecha de creación:</label>
        <br>

        <input
            type="text"
            name="fechaCreacion"
            value="<%= envio.getFechaCreacion() %>"
            required
        >
    </p>

    <p>
        <label>Valor total:</label>
        <br>

        <input
            type="number"
            step="0.01"
            name="valorTotal"
            value="<%= envio.getValorTotal() %>"
            required
        >
    </p>

    <p>
        <label>Observaciones:</label>
        <br>

        <textarea
            name="observaciones"
            rows="5"
            cols="40"
        ><%= envio.getObservaciones() %></textarea>
    </p>

    <button type="submit">
        Guardar cambios
    </button>

</form>

<%
    } else {
%>

<p>
    No se encontró información del envío.
</p>

<%
    }
%>

<br>

<a href="ConsultaEnvioServlet">
    Cancelar y volver al listado
</a>

</body>
</html>