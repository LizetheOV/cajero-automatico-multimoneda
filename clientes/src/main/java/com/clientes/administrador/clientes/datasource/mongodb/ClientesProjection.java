

package com.clientes.administrador.clientes.datasource.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.datasource.mongodb
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 10:42
 * <p>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clientes")
public class ClientesProjection {
  @Id
  private String id;
  private String codigo;
  private String ci;
  private String email;
  private String nombre;
  private String apellido;
  private Integer telefono;
  private Date fechaHoraRegistro;
  private String estado;

}
