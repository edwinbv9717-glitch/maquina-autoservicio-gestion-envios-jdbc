<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.maquinaenvios.modelo.Envio"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        Listado de Envíos
    </title>

</head>

<body>

    <h1>
        Listado de Envíos
    </h1>

    <hr>

    <%
        List<Envio> envios =
                (List<Envio>) request.getAttribute("envios");

        if (envios != null && !envios.isEmpty()) {

            for (Envio envio : envios) {
    %>

        <div>

            <h2>
                Envío #<%= envio.getIdEnvio() %>
            </h2>

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
                <strong>Fecha de creación:</strong>
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

            <br>

            <!-- BOTÓN EDITAR -->

            <a href="EditarEnvioServlet?idEnvio=<%= envio.getIdEnvio() %>">

                <button type="button">
                    Editar
                </button>

            </a>

            &nbsp;&nbsp;

            <!-- BOTÓN ELIMINAR -->

            <form
                action="EliminarEnvioServlet"
                method="post"
                style="display:inline;"
                onsubmit="return confirm('¿Está seguro de eliminar este envío?');"
            >

                <input
                    type="hidden"
                    name="idEnvio"
                    value="<%= envio.getIdEnvio() %>"
                >

                <button type="submit">
                    Eliminar
                </button>

            </form>

            <hr>

        </div>

    <%
            }

        } else {
    %>

        <p>
            No hay envíos registrados.
        </p>

    <%
        }
    %>

    <br>

    <a href="index.jsp">
        Volver al inicio
    </a>

    <br><br>

    <a href="formulario-envio.jsp">
        Registrar nuevo envío
    </a>

    <br><br>

    <a href="buscar-envio.jsp">
        Buscar envío por ID
    </a>

</body>

</html>