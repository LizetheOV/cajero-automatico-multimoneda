
package com.transferencias.administrador.transacciones.business.utils;

import com.google.gson.Gson;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.business.utils
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 15:12
 * <p>
 */

public class ObjectParseUtils {

  public static <T> T parseObjectFromString(String s, Class<T> clazz) {

    return new Gson().fromJson(s, clazz);

  }

}
