

package com.administrador.tasas.datasources.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.datasources.mongodb
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 07:30
 * <p>
 */

public interface TasaStorageRepositoryFacade extends MongoRepository<TasaProjection, String> {
  @Query("{'origen': ?0, 'destino': ?1, 'timestamp': {'$gte': ?2, '$lte': ?3}}")
  TasaProjection findByOrigenAndDestinoAndTimestampIsBetween(String origen, String destino, Long inicio, Long fin);
}
