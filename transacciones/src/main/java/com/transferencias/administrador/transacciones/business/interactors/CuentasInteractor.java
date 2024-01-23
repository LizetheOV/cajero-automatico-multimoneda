
package com.transferencias.administrador.transacciones.business.interactors;

import com.transferencias.administrador.transacciones.business.repositories.CuentasRepository;
import com.transferencias.administrador.transacciones.transport.dto.CuentaDTO;
import org.springframework.stereotype.Service;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.business.interactors
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 18:47
 * <p>
 */

@Service
public class CuentasInteractor {

  private CuentasRepository cuentasRepository;

  public CuentasInteractor(CuentasRepository cuentasRepository) {
    this.cuentasRepository = cuentasRepository;
  }

  public void registrarCuenta(CuentaDTO cuentaDTO) {

    cuentasRepository.registrarCuenta(
        cuentaDTO.getCodigo(),
        cuentaDTO.getMoneda(),
        cuentaDTO.getSaldo(),
        cuentaDTO.getEstado()
    );

  }
}
