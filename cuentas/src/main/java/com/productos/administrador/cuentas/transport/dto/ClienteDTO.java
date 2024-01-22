
package com.productos.administrador.cuentas.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.transport.listeners
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 15:14
 * <p>
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ClienteDTO {
  private String codigo;
  private String estado;

}
