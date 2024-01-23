
package com.transferencias.administrador.transacciones.business.interactors;

import com.transferencias.administrador.transacciones.business.exceptions.CuentaConSaldoInsuficienteException;
import com.transferencias.administrador.transacciones.business.exceptions.CuentaNoEncontradaException;
import com.transferencias.administrador.transacciones.business.models.Cuenta;
import com.transferencias.administrador.transacciones.business.models.Transaccion;
import com.transferencias.administrador.transacciones.business.repositories.CuentasRepository;
import com.transferencias.administrador.transacciones.business.repositories.TipoCambioRepository;
import com.transferencias.administrador.transacciones.business.repositories.TransaccionesRepository;
import com.transferencias.administrador.transacciones.transport.dto.TipoTransaccion;
import com.transferencias.administrador.transacciones.transport.dto.TransaccionRequestDTO;
import com.transferencias.administrador.transacciones.transport.dto.TransaccionResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.business.interactors
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 06:56
 * <p>
 */

@Service
public class TransaccionesInteractor {

  private TransaccionesRepository transaccionesRepository;
  private CuentasRepository cuentasRepository;
  private TipoCambioRepository tipoCambioRepository;

  public TransaccionesInteractor(
      TransaccionesRepository transaccionesRepository,
      CuentasRepository cuentasRepository,
      TipoCambioRepository tipoCambioRepository
  ) {
    this.transaccionesRepository = transaccionesRepository;
    this.cuentasRepository = cuentasRepository;
    this.tipoCambioRepository = tipoCambioRepository;
  }

  public TransaccionResponseDTO registrarTransaccion(TransaccionRequestDTO requestDTO, TipoTransaccion tipoTransaccion)
      throws CuentaNoEncontradaException, CuentaConSaldoInsuficienteException {

    Cuenta cuenta = cuentasRepository.obtenerCuenta(requestDTO.getCuentaID().toString());
    String monedaCuenta = cuenta.getMoneda();
    String monedaTransaccion = requestDTO.getMoneda().name();
    BigDecimal montoTransaccion = requestDTO.getMonto();
    BigDecimal saldoInicial = cuenta.getSaldo();
    BigDecimal tipoCambio = BigDecimal.ONE;

    if (!monedaCuenta.equals(monedaTransaccion)) {

      tipoCambio = tipoCambioRepository.getTipoCambio(monedaTransaccion, monedaCuenta);

    }

    montoTransaccion = montoTransaccion.multiply(tipoCambio);

    if (tipoTransaccion.equals(TipoTransaccion.RETIRO) && saldoInicial.compareTo(montoTransaccion) < 0) {

      throw new CuentaConSaldoInsuficienteException(
          String.format(
              "Cuenta con fondos insuficientes, intenta retirar: %s, pero la cuenta tiene %s",
              montoTransaccion,
              saldoInicial
          )
      );

    }

    if (tipoTransaccion.equals(TipoTransaccion.RETIRO)) {

      cuenta.setSaldo(saldoInicial.subtract(montoTransaccion));

    } else {

      cuenta.setSaldo(saldoInicial.add(montoTransaccion));

    }

    cuentasRepository.actualizarSaldo(cuenta);

    UUID transaccionID = UUID.randomUUID();
    transaccionesRepository.registrarTransaccion(
        cuenta,
        new Transaccion(
            transaccionID.toString(),
            tipoTransaccion.name(),
            saldoInicial,
            requestDTO.getMonto(),
            monedaTransaccion,
            tipoCambio,
            montoTransaccion
        )
    );

    TransaccionResponseDTO responseDTO = new TransaccionResponseDTO();
    responseDTO.setTransaccionID(transaccionID);
    responseDTO.setCuentaID(requestDTO.getCuentaID());
    responseDTO.setMoneda(TransaccionResponseDTO.MonedaEnum.valueOf(monedaCuenta));
    responseDTO.setSaldo(cuenta.getSaldo());

    return responseDTO;

  }

}
