package com.marketplace.perfiles.domain.port.out;

import com.marketplace.perfiles.domain.model.Profesional;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de SALIDA del subsistema Perfiles.
 * El dominio define esta interfaz; la infraestructura la implementa (adaptador JPA).
 * Asi el dominio no depende de la base de datos (inversion de dependencias).
 */
public interface ProfesionalRepositoryPort {
    Profesional guardar(Profesional profesional);
    Optional<Profesional> buscarPorId(Long id);
    List<Profesional> buscarActivos();
    List<Profesional> buscarTodos();
}
