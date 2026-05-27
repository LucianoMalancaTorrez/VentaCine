package com.mycompany.ventacine.model;

/**
 * Interfaz para aplicar descuentos promocionales.
 * Las películas implementan esta interfaz para ofrecer
 * un porcentaje de descuento sobre el precio de la entrada.
 *
 * @author USUARIO
 */
public interface IPromocion {

    /**
     * Obtiene el porcentaje de descuento de la promoción.
     *
     * @return porcentaje de descuento como valor float
     */
    float obtenerDescuento();
}
