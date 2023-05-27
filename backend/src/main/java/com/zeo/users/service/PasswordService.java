package com.zeo.users.service;

/**
 * Servicio para hashear contraseñas
 */
public interface PasswordService {
    /**
     * Hashea una contraseña
     *
     * @param password La contraseña en texto plano
     * @return El valor hasheado
     */
    String hash(final String password);

    /**
     * Determina si la contraseña corresponde con el hash
     *
     * @param password La contraseña en texto plano
     * @param hash     La contraseña codificada
     * @return Si coinciden los valores
     */
    boolean matches(final String password, final String hash);
}
