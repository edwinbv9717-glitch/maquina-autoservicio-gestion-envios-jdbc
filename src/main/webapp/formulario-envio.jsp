<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Registrar Envío</title>

</head>

<body>

    <h1>Registrar Nuevo Envío</h1>

    <form action="EnvioServlet" method="post">

        <label for="numeroGuia">
            Número de guía:
        </label>

        <br>

        <input
            type="text"
            id="numeroGuia"
            name="numeroGuia"
            required
        >

        <br><br>


        <label for="idUsuario">
            ID del usuario:
        </label>

        <br>

        <input
            type="number"
            id="idUsuario"
            name="idUsuario"
            required
        >

        <br><br>


        <label for="idRemitente">
            ID del remitente:
        </label>

        <br>

        <input
            type="number"
            id="idRemitente"
            name="idRemitente"
            required
        >

        <br><br>


        <label for="idDestinatario">
            ID del destinatario:
        </label>

        <br>

        <input
            type="number"
            id="idDestinatario"
            name="idDestinatario"
            required
        >

        <br><br>


        <label for="idCiudadOrigen">
            ID ciudad origen:
        </label>

        <br>

        <input
            type="number"
            id="idCiudadOrigen"
            name="idCiudadOrigen"
            required
        >

        <br><br>


        <label for="idCiudadDestino">
            ID ciudad destino:
        </label>

        <br>

        <input
            type="number"
            id="idCiudadDestino"
            name="idCiudadDestino"
            required
        >

        <br><br>


        <label for="idServicio">
            ID servicio:
        </label>

        <br>

        <input
            type="number"
            id="idServicio"
            name="idServicio"
            required
        >

        <br><br>


        <label for="idEstado">
            ID estado:
        </label>

        <br>

        <input
            type="number"
            id="idEstado"
            name="idEstado"
            required
        >

        <br><br>


        <label for="valorTotal">
            Valor total:
        </label>

        <br>

        <input
            type="number"
            step="0.01"
            id="valorTotal"
            name="valorTotal"
            required
        >

        <br><br>


        <label for="observaciones">
            Observaciones:
        </label>

        <br>

        <textarea
            id="observaciones"
            name="observaciones"
            rows="4"
            cols="40"
        ></textarea>

        <br><br>


        <button type="submit">
            Registrar Envío
        </button>

    </form>

    <br>

    <a href="index.jsp">
        Volver al inicio
    </a>

</body>

</html>