/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.administrador.tasas.datasources.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.datasources.mongodb
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 07:21
 * <p>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasas")
public class TasasProjection {
  @Id
  private String id;
  private String origen;
  private String destino;
  private BigDecimal cotizacion;
  private Long timestamp;
  private Date fechaHoraRegistro;

}
