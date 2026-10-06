package com.marketplace.favoritos.domain.port.in;

import java.util.List;

/**
 * Puerto de ENTRADA: el cliente gestiona sus favoritos (caso de uso "Gestionar Favoritos").
 */
public interface GestionarFavoritosUseCase {
    /** Ids de los tecnicos favoritos de un cliente. */
    List<Long> listar(Long idCliente);
    void agregar(Long idCliente, Long idProfesional);
    void quitar(Long idCliente, Long idProfesional);
}
