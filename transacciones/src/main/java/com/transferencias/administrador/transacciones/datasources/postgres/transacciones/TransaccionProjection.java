
package com.transferencias.administrador.transacciones.datasources.postgres.transacciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.datasources.postgres.transacciones
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 06:05
 * <p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transacciones")
public class TransaccionProjection {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "codigo")
  private String codigo;
  @Column(name = "cuenta_id")
  private String cuentaID;
  @Column(name = "saldo_inicial")
  private BigDecimal saldoInicial;
  @Column(name = "saldo_final")
  private BigDecimal saldoFinal;
  @Column(name = "tipo")
  private String tipo;
  @Column(name = "monto_recibido")
  private BigDecimal montoRecibido;
  @Column(name = "moneda_recibida")
  private String monedaRecibida;
  @Column(name = "cotizacion")
  private BigDecimal cotizacion;
  @Column(name = "monto_transaccion")
  private BigDecimal montoTransaccion;
  @Column(name = "fecha_hora_registro")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaHoraRegistro;

}
