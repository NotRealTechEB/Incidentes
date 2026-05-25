package cl.dgac.incidentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.dgac.incidentes.model.ModeloTipoIncidente;

@Repository
public interface RepositorioTipoIncidente extends JpaRepository<ModeloTipoIncidente,Long>{
    ModeloTipoIncidente findByTipo(String tipo);
}
