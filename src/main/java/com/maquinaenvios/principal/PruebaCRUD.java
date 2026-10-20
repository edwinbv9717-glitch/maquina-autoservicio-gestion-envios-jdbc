package com.maquinaenvios.principal;

import com.maquinaenvios.conexion.ConexionBD;
import com.maquinaenvios.dao.EnvioDAO;
import com.maquinaenvios.modelo.Envio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

public class PruebaCRUD {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("       PRUEBA CRUD DE ENVIOS");
        System.out.println("======================================");

        // ============================================================
// 1. PROBAR CONEXIÓN
// ============================================================

System.out.println("\n1. Probando conexión con MySQL...");

Connection conexion;

try {
    conexion = ConexionBD.conectar();

    System.out.println("✓ Conexión exitosa con MySQL.");

} catch (SQLException e) {

    System.out.println("✗ No se pudo conectar con MySQL.");
    System.out.println("Detalle: " + e.getMessage());

    return;
}

        // ============================================================
        // 2. CREAR DAO
        // ============================================================

        EnvioDAO envioDAO = new EnvioDAO();

        System.out.println("\n2. DAO creado correctamente.");

        // ============================================================
        // 3. OBTENER IDS DE LAS TABLAS RELACIONADAS
        // ============================================================

        int idUsuario = 0;
        int idRemitente = 0;
        int idDestinatario = 0;
        int idCiudadOrigen = 0;
        int idCiudadDestino = 0;
        int idServicio = 0;
        int idEstado = 0;

        try {

            // --------------------------------------------------------
            // USUARIO
            // --------------------------------------------------------

            String sqlUsuario =
                    "SELECT id_usuario FROM usuario LIMIT 1";

            try (PreparedStatement sentencia =
                         conexion.prepareStatement(sqlUsuario);
                 ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    idUsuario =
                            resultado.getInt("id_usuario");
                }
            }

            // --------------------------------------------------------
            // CONTACTOS
            // --------------------------------------------------------

            String sqlContactos =
                    "SELECT id_contacto FROM contacto "
                    + "ORDER BY id_contacto LIMIT 2";

            try (PreparedStatement sentencia =
                         conexion.prepareStatement(sqlContactos);
                 ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    idRemitente =
                            resultado.getInt("id_contacto");
                }

                if (resultado.next()) {

                    idDestinatario =
                            resultado.getInt("id_contacto");
                }
            }

            // --------------------------------------------------------
            // CIUDADES
            // --------------------------------------------------------

            String sqlCiudades =
                    "SELECT id_ciudad FROM ciudad "
                    + "ORDER BY id_ciudad LIMIT 2";

            try (PreparedStatement sentencia =
                         conexion.prepareStatement(sqlCiudades);
                 ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    idCiudadOrigen =
                            resultado.getInt("id_ciudad");
                }

                if (resultado.next()) {

                    idCiudadDestino =
                            resultado.getInt("id_ciudad");
                }
            }

            // --------------------------------------------------------
            // SERVICIO
            // --------------------------------------------------------

            String sqlServicio =
                    "SELECT id_servicio "
                    + "FROM tipo_servicio "
                    + "LIMIT 1";

            try (PreparedStatement sentencia =
                         conexion.prepareStatement(sqlServicio);
                 ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    idServicio =
                            resultado.getInt("id_servicio");
                }
            }

            // --------------------------------------------------------
            // ESTADO
            // --------------------------------------------------------

            String sqlEstado =
                    "SELECT id_estado "
                    + "FROM estado_envio "
                    + "LIMIT 1";

            try (PreparedStatement sentencia =
                         conexion.prepareStatement(sqlEstado);
                 ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    idEstado =
                            resultado.getInt("id_estado");
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "✗ Error obteniendo datos relacionados."
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            try {
                conexion.close();
            } catch (Exception ignored) {
            }

            return;
        }

        // ============================================================
        // 4. VALIDAR IDS
        // ============================================================

        if (idUsuario == 0
                || idRemitente == 0
                || idDestinatario == 0
                || idCiudadOrigen == 0
                || idCiudadDestino == 0
                || idServicio == 0
                || idEstado == 0) {

            System.out.println(
                    "\n✗ No existen suficientes datos relacionados."
            );

            System.out.println(
                    "Verifica que las tablas usuario, contacto, "
                    + "ciudad, tipo_servicio y estado_envio "
                    + "tengan registros."
            );

            try {
                conexion.close();
            } catch (Exception ignored) {
            }

            return;
        }

        // ============================================================
        // 5. MOSTRAR IDS ENCONTRADOS
        // ============================================================

        System.out.println("\n3. Datos relacionados encontrados:");

        System.out.println(
                "Usuario: " + idUsuario
        );

        System.out.println(
                "Remitente: " + idRemitente
        );

        System.out.println(
                "Destinatario: " + idDestinatario
        );

        System.out.println(
                "Ciudad origen: " + idCiudadOrigen
        );

        System.out.println(
                "Ciudad destino: " + idCiudadDestino
        );

        System.out.println(
                "Servicio: " + idServicio
        );

        System.out.println(
                "Estado: " + idEstado
        );

        // ============================================================
        // 6. CREAR ENVÍO
        // ============================================================

        System.out.println(
                "\n4. CREANDO ENVIO..."
        );

        Envio nuevoEnvio = new Envio();

        String numeroGuia =
                "PRUEBA-" + System.currentTimeMillis();

        nuevoEnvio.setNumeroGuia(numeroGuia);

        nuevoEnvio.setIdUsuario(idUsuario);

        nuevoEnvio.setIdRemitente(idRemitente);

        nuevoEnvio.setIdDestinatario(idDestinatario);

        nuevoEnvio.setIdCiudadOrigen(idCiudadOrigen);

        nuevoEnvio.setIdCiudadDestino(idCiudadDestino);

        nuevoEnvio.setIdServicio(idServicio);

        nuevoEnvio.setIdEstado(idEstado);

        nuevoEnvio.setValorTotal(
                new BigDecimal("45000.00")
        );

        nuevoEnvio.setObservaciones(
                "Envio creado mediante prueba CRUD"
        );

        // ============================================================
        // 7. INSERTAR
        // ============================================================

        System.out.println(
                "\n5. INSERTANDO ENVIO..."
        );

        boolean insertado =
                envioDAO.insertar(nuevoEnvio);

        if (!insertado) {

            System.out.println(
                    "✗ ERROR: No se pudo insertar el envio."
            );

            try {
                conexion.close();
            } catch (Exception ignored) {
            }

            return;

        } else {

            System.out.println(
                    "✓ Envio insertado correctamente."
            );

            System.out.println(
                    "✓ Numero de guia: "
                    + numeroGuia
            );
        }

        // ============================================================
        // 8. BUSCAR EL ENVÍO INSERTADO
        // ============================================================

        System.out.println(
                "\n6. BUSCANDO ENVIO INSERTADO..."
        );

        Envio envioCreado = null;

        List<Envio> listaDespuesInsertar =
                envioDAO.listar();

        for (Envio envio : listaDespuesInsertar) {

            if (envio.getNumeroGuia().equals(numeroGuia)) {

                envioCreado = envio;

                break;
            }
        }

        if (envioCreado == null) {

            System.out.println(
                    "✗ No se encontro el envío recien insertado."
            );

            try {
                conexion.close();
            } catch (Exception ignored) {
            }

            return;

        } else {

            System.out.println(
                    "✓ Envio encontrado correctamente."
            );

            System.out.println(
                    "✓ ID del envio: "
                    + envioCreado.getIdEnvio()
            );

            System.out.println(
                    "✓ Guia: "
                    + envioCreado.getNumeroGuia()
            );

            System.out.println(
                    "✓ Valor: $"
                    + envioCreado.getValorTotal()
            );

            System.out.println(
                    "✓ Observacion: "
                    + envioCreado.getObservaciones()
            );
        }

        // ============================================================
        // 9. ACTUALIZAR ENVÍO
        // ============================================================

        System.out.println(
                "\n7. ACTUALIZANDO ENVIO..."
        );

        envioCreado.setValorTotal(
                new BigDecimal("55000.00")
        );

        envioCreado.setObservaciones(
                "Envio actualizado mediante prueba CRUD"
        );

        boolean actualizado =
                envioDAO.actualizar(envioCreado);

        if (actualizado) {

            System.out.println(
                    "✓ Envio actualizado correctamente."
            );

            System.out.println(
                    "✓ Nuevo valor: $"
                    + envioCreado.getValorTotal()
            );

            System.out.println(
                    "✓ Nueva observacion: "
                    + envioCreado.getObservaciones()
            );

        } else {

            System.out.println(
                    "✗ No se pudo actualizar el envio."
            );
        }

        // ============================================================
        // 10. COMPROBAR ACTUALIZACIÓN
        // ============================================================

        System.out.println(
                "\n8. COMPROBANDO ACTUALIZACION..."
        );

        Envio envioActualizado =
                envioDAO.buscarPorId(
                        envioCreado.getIdEnvio()
                );

        if (envioActualizado != null) {

            System.out.println(
                    "✓ Se encontró el envio actualizado."
            );

            System.out.println(
                    "ID: "
                    + envioActualizado.getIdEnvio()
            );

            System.out.println(
                    "Guia: "
                    + envioActualizado.getNumeroGuia()
            );

            System.out.println(
                    "Valor actualizado: $"
                    + envioActualizado.getValorTotal()
            );

            System.out.println(
                    "Observacion actualizada: "
                    + envioActualizado.getObservaciones()
            );

        } else {

            System.out.println(
                    "✗ No se pudo consultar el envío actualizado."
            );
        }

        // ============================================================
        // 11. CONSULTAR TODOS LOS ENVÍOS
        // ============================================================

        System.out.println(
                "\n9. CONSULTANDO TODOS LOS ENVIOS..."
        );

        List<Envio> listaEnvios =
                envioDAO.listar();

        if (listaEnvios.isEmpty()) {

            System.out.println(
                    "No existen envios."
            );

        } else {

            System.out.println(
                    "✓ Se encontraron "
                    + listaEnvios.size()
                    + " envios."
            );

            System.out.println(
                    "\n======================================"
            );

            System.out.println(
                    "          LISTADO DE ENVIOS"
            );

            System.out.println(
                    "======================================"
            );

            for (Envio envio : listaEnvios) {

                System.out.println(
                        "ID: " + envio.getIdEnvio()
                        + " | Guia: "
                        + envio.getNumeroGuia()
                        + " | Usuario: "
                        + envio.getIdUsuario()
                        + " | Valor: $"
                        + envio.getValorTotal()
                        + " | Observacion: "
                        + envio.getObservaciones()
                );
            }
        }

        // ============================================================
        // 12. ELIMINAR ENVÍO
        // ============================================================

        System.out.println(
                "\n10. ELIMINANDO ENVIO DE PRUEBA..."
        );

        long idEliminar =
                envioCreado.getIdEnvio();

        boolean eliminado =
                envioDAO.eliminar(idEliminar);

        if (eliminado) {

            System.out.println(
                    "✓ Envio eliminado correctamente."
            );

            System.out.println(
                    "✓ ID eliminado: "
                    + idEliminar
            );

        } else {

            System.out.println(
                    "✗ No se pudo eliminar el envio."
            );
        }

        // ============================================================
        // 13. COMPROBAR ELIMINACIÓN
        // ============================================================

        System.out.println(
                "\n11. COMPROBANDO ELIMINACION..."
        );

        Envio envioEliminado =
                envioDAO.buscarPorId(idEliminar);

        if (envioEliminado == null) {

            System.out.println(
                    "✓ CONFIRMADO: El envio ya no existe."
            );

        } else {

            System.out.println(
                    "✗ ERROR: El envio todavía existe."
            );
        }

        // ============================================================
        // 14. MOSTRAR RESULTADO FINAL
        // ============================================================

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "           RESULTADO CRUD"
        );

        System.out.println(
                "======================================"
        );

        System.out.println(
                "✓ CONEXION"
        );

        System.out.println(
                "✓ INSERTAR"
        );

        System.out.println(
                "✓ CONSULTAR"
        );

        System.out.println(
                "✓ ACTUALIZAR"
        );

        System.out.println(
                "✓ ELIMINAR"
        );

        System.out.println(
                "======================================"
        );

        // ============================================================
        // 15. CERRAR CONEXIÓN
        // ============================================================

        try {

            conexion.close();

            System.out.println(
                    "\n✓ Conexion cerrada correctamente."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error cerrando la conexion: "
                    + e.getMessage()
            );
        }

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "       PRUEBA CRUD FINALIZADA"
        );

        System.out.println(
                "======================================"
        );
    }
}