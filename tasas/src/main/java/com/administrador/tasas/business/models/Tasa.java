

package com.administrador.tasas.business.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.business.models
 * <p>
 * User: LOvandoV
 * Date: 19/1/2024
 * Time: 19:12
 * <p>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tasa {
  BigDecimal cotizacion;
  Long timestamp;

}
