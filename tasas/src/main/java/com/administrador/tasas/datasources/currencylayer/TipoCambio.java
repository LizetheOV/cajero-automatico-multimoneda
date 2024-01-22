

package com.administrador.tasas.datasources.currencylayer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.datasources.currencylayer
 * <p>
 * User: LOvandoV
 * Date: 12/1/2024
 * Time: 20:02
 * <p>
 */

@Data
@NoArgsConstructor
public class TipoCambio {
  Long timestamp;
  String source;
  Map<String, BigDecimal> quotes;
}
