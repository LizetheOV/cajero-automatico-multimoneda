
package com.clientes.administrador.clientes.business.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.business.models
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 10:37
 * <p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
  private UUID codigo;
  private String ci;
  private String email;
  private String nombre;
  private String apellido;
  private Integer telefono;
  private String estado;

}
