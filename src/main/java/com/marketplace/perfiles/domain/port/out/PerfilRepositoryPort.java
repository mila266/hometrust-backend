package com.marketplace.perfiles.domain.port.out;

import com.marketplace.perfiles.domain.model.PerfilProfesional;
import java.util.Optional;

/** Puerto de SALIDA para el perfil del tecnico. */
public interface PerfilRepositoryPort {
    Optional<PerfilProfesional> buscarPorProfesional(Long idProfesional);
    PerfilProfesional guardar(PerfilProfesional perfil);
}
