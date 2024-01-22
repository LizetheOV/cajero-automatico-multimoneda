/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.productos.administrador.cuentas.datasource.postgres.cuentas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.datasource.cuentas
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 17:07
 * <p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cuentas")
public class CuentasProjection {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "codigo", unique = true)
  private String codigo;
  @Column(name = "clienteID")
  private String clienteID;
  @Column(name = "saldo")
  private BigDecimal saldo;
  @Column(name = "estado")
  private String estado;
  @Column(name = "moneda")
  private String moneda;
  @Column(name = "fechaHoraRegistro")
  private Date fechaHoraRegistro;
  @Column(name = "fechaHoraActualizacion")
  private Date fechaHoraActualizacion;

}
