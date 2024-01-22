
package com.productos.administrador.cuentas.datasource.postgres.clientes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.datasource.postgres.clientes
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 16:46
 * <p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clientes")
public class ClientesProjection {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "codigo", unique = true)
  private String codigo;
  @Column(name = "estado")
  private String estado;

}
