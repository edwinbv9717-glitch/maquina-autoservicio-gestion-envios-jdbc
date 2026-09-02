package com.maquinaenvios.dao;

import com.maquinaenvios.conexion.ConexionBD;
import com.maquinaenvios.modelo.Envio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnvioDAO {

    // ============================================================
    // MÉTODO 1: INSERTAR ENVÍO
    // ============================================================

    public boolean insertar(Envio envio) {

        String sql = """
                INSERT INTO envio (
                    numero_guia,
                    id_usuario,
                    id_remitente,
                    id_destinatario,
                    id_ciudad_origen,
                    id_ciudad_destino,
                    id_servicio,
                    id_estado,
                    valor_total,
                    observaciones
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, envio.getNumeroGuia());
            sentencia.setInt(2, envio.getIdUsuario());
            sentencia.setInt(3, envio.getIdRemitente());
            sentencia.setInt(4, envio.getIdDestinatario());
            sentencia.setInt(5, envio.getIdCiudadOrigen());
            sentencia.setInt(6, envio.getIdCiudadDestino());
            sentencia.setInt(7, envio.getIdServicio());
            sentencia.setInt(8, envio.getIdEstado());
            sentencia.setBigDecimal(9, envio.getValorTotal());
            sentencia.setString(10, envio.getObservaciones());

            int filasAfectadas = sentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al insertar el envío: "
                    + e.getMessage());

            return false;
        }
    }


    // ============================================================
    // MÉTODO 2: CONSULTAR TODOS LOS ENVÍOS
    // ============================================================

    public List<Envio> listar() {

        List<Envio> listaEnvios = new ArrayList<>();

        String sql = """
                SELECT
                    id_envio,
                    numero_guia,
                    id_usuario,
                    id_remitente,
                    id_destinatario,
                    id_ciudad_origen,
                    id_ciudad_destino,
                    id_servicio,
                    id_estado,
                    fecha_creacion,
                    valor_total,
                    observaciones
                FROM envio
                ORDER BY id_envio
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Envio envio = new Envio();

                envio.setIdEnvio(
                        resultado.getLong("id_envio")
                );

                envio.setNumeroGuia(
                        resultado.getString("numero_guia")
                );

                envio.setIdUsuario(
                        resultado.getInt("id_usuario")
                );

                envio.setIdRemitente(
                        resultado.getInt("id_remitente")
                );

                envio.setIdDestinatario(
                        resultado.getInt("id_destinatario")
                );

                envio.setIdCiudadOrigen(
                        resultado.getInt("id_ciudad_origen")
                );

                envio.setIdCiudadDestino(
                        resultado.getInt("id_ciudad_destino")
                );

                envio.setIdServicio(
                        resultado.getInt("id_servicio")
                );

                envio.setIdEstado(
                        resultado.getInt("id_estado")
                );

                envio.setFechaCreacion(
                        resultado.getTimestamp("fecha_creacion")
                );

                envio.setValorTotal(
                        resultado.getBigDecimal("valor_total")
                );

                envio.setObservaciones(
                        resultado.getString("observaciones")
                );

                listaEnvios.add(envio);
            }

        } catch (SQLException e) {

            System.out.println("Error al consultar los envíos: "
                    + e.getMessage());
        }

        return listaEnvios;
    }


    // ============================================================
    // MÉTODO 3: BUSCAR ENVÍO POR ID
    // ============================================================

    public Envio buscarPorId(long idEnvio) {

        String sql = """
                SELECT
                    id_envio,
                    numero_guia,
                    id_usuario,
                    id_remitente,
                    id_destinatario,
                    id_ciudad_origen,
                    id_ciudad_destino,
                    id_servicio,
                    id_estado,
                    fecha_creacion,
                    valor_total,
                    observaciones
                FROM envio
                WHERE id_envio = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setLong(1, idEnvio);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    Envio envio = new Envio();

                    envio.setIdEnvio(
                            resultado.getLong("id_envio")
                    );

                    envio.setNumeroGuia(
                            resultado.getString("numero_guia")
                    );

                    envio.setIdUsuario(
                            resultado.getInt("id_usuario")
                    );

                    envio.setIdRemitente(
                            resultado.getInt("id_remitente")
                    );

                    envio.setIdDestinatario(
                            resultado.getInt("id_destinatario")
                    );

                    envio.setIdCiudadOrigen(
                            resultado.getInt("id_ciudad_origen")
                    );

                    envio.setIdCiudadDestino(
                            resultado.getInt("id_ciudad_destino")
                    );

                    envio.setIdServicio(
                            resultado.getInt("id_servicio")
                    );

                    envio.setIdEstado(
                            resultado.getInt("id_estado")
                    );

                    envio.setFechaCreacion(
                            resultado.getTimestamp("fecha_creacion")
                    );

                    envio.setValorTotal(
                            resultado.getBigDecimal("valor_total")
                    );

                    envio.setObservaciones(
                            resultado.getString("observaciones")
                    );

                    return envio;
                }
            }

        } catch (SQLException e) {

            System.out.println("Error al buscar el envío: "
                    + e.getMessage());
        }

        return null;
    }


    // ============================================================
    // MÉTODO 4: ACTUALIZAR ENVÍO
    // ============================================================

   public boolean actualizar(Envio envio) {

    String sql = "UPDATE envio SET "
            + "numero_guia = ?, "
            + "id_usuario = ?, "
            + "id_remitente = ?, "
            + "id_destinatario = ?, "
            + "id_ciudad_origen = ?, "
            + "id_ciudad_destino = ?, "
            + "id_servicio = ?, "
            + "id_estado = ?, "
            + "valor_total = ?, "
            + "observaciones = ? "
            + "WHERE id_envio = ?";

    try (Connection conexion = ConexionBD.conectar();
         PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setString(1, envio.getNumeroGuia());

        ps.setInt(2, envio.getIdUsuario());

        ps.setInt(3, envio.getIdRemitente());

        ps.setInt(4, envio.getIdDestinatario());

        ps.setInt(5, envio.getIdCiudadOrigen());

        ps.setInt(6, envio.getIdCiudadDestino());

        ps.setInt(7, envio.getIdServicio());

        ps.setInt(8, envio.getIdEstado());

        ps.setBigDecimal(9, envio.getValorTotal());

        ps.setString(10, envio.getObservaciones());

        ps.setLong(11, envio.getIdEnvio());

        int filasActualizadas = ps.executeUpdate();

        return filasActualizadas > 0;

    } catch (SQLException e) {

        System.out.println("Error al actualizar envío: " + e.getMessage());

        return false;
    }
}
    // ============================================================
    // MÉTODO 5: ELIMINAR ENVÍO
    // ============================================================

    public boolean eliminar(long idEnvio) {

        String sql = """
                DELETE FROM envio
                WHERE id_envio = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setLong(1, idEnvio);

            int filasAfectadas = sentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar el envío: "
                    + e.getMessage());

            return false;
        }
    }
}