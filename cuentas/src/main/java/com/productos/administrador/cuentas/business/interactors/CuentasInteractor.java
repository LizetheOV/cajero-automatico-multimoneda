
package com.productos.administrador.cuentas.business.interactors;

import com.productos.administrador.cuentas.business.exceptions.ClienteNoEncontradoException;
import com.productos.administrador.cuentas.business.exceptions.CuentaNoEncontradaException;
import com.productos.administrador.cuentas.business.models.Cuenta;
import com.productos.administrador.cuentas.business.repositories.ClientesRepository;
import com.productos.administrador.cuentas.business.repositories.CuentasRepository;
import com.productos.administrador.cuentas.business.repositories.TipoCambioRepository;
import com.productos.administrador.cuentas.transport.dto.CuentaDTO;
import com.productos.administrador.cuentas.transport.dto.SaldoCuentaDTO;
import com.productos.administrador.cuentas.transport.dto.SaldoDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.business.interactors
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 17:35
 * <p>
 */

@Service
public class CuentasInteractor {

  private ClientesRepository clientesRepository;
  private CuentasRepository cuentasRepository;
  private TipoCambioRepository tipoCambioRepository;

  public CuentasInteractor(
      ClientesRepository clientesRepository,
      CuentasRepository cuentasRepository,
      TipoCambioRepository tipoCambioRepository
  ) {
    this.clientesRepository = clientesRepository;
    this.cuentasRepository = cuentasRepository;
    this.tipoCambioRepository = tipoCambioRepository;
  }


  public CuentaDTO guardarCuenta(CuentaDTO cuentaDTO) throws ClienteNoEncontradoException {

    if (!clientesRepository.existeCliente(cuentaDTO.getClienteID().toString())) {

      throw new ClienteNoEncontradoException(
          String.format("No se encontro registro de cliente con identificación: %s", cuentaDTO.getClienteID())
      );

    }

    UUID codigo = UUID.randomUUID();
    BigDecimal saldoInicial = BigDecimal.valueOf(0);

    cuentasRepository.guardarCuenta(
        new Cuenta(
            codigo.toString(),
            cuentaDTO.getClienteID().toString(),
            saldoInicial,
            cuentaDTO.getMoneda().name(),
            "ACTIVO"
        )
    );
    cuentaDTO.setId(codigo);
    cuentaDTO.setSaldo(saldoInicial);

    return cuentaDTO;

  }

  public CuentaDTO obtenerCuenta(String codigo) throws CuentaNoEncontradaException {

    Cuenta cuenta = cuentasRepository.obtenerCuenta(codigo);

    CuentaDTO cuentaDTO = new CuentaDTO();
    cuentaDTO.setId(UUID.fromString(cuenta.getCodigo()));
    cuentaDTO.setClienteID(UUID.fromString(cuenta.getClienteID()));
    cuentaDTO.setMoneda(CuentaDTO.MonedaEnum.valueOf(cuenta.getMoneda()));
    cuentaDTO.setSaldo(cuenta.getSaldo());

    return cuentaDTO;

  }

  public SaldoDTO obtenerSaldo(String codigo, String moneda) throws CuentaNoEncontradaException {

    Cuenta cuenta = cuentasRepository.obtenerCuenta(codigo);
    BigDecimal monto = cuenta.getSaldo();

    if (monto.compareTo(BigDecimal.ZERO) > 0 && !cuenta.getMoneda().equals(moneda)) {

      BigDecimal tipoCambio = tipoCambioRepository.getTipoCambio(cuenta.getMoneda(), moneda);
      monto = monto.multiply(tipoCambio);

    }

    SaldoDTO saldoDTO = new SaldoDTO();
    saldoDTO.setMoneda(SaldoDTO.MonedaEnum.valueOf(moneda));
    saldoDTO.setMonto(monto);

    return saldoDTO;

  }

  public void actualizarSaldo(SaldoCuentaDTO saldoCuentaDTO) {
    cuentasRepository.actualizarSaldo(saldoCuentaDTO.getCodigo(), saldoCuentaDTO.getSaldo());
  }

}
