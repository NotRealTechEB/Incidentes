package cl.dgac.incidentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.dgac.incidentes.model.ModeloTipoIncidente;


public interface RepositorioTipoIncidente extends JpaRepository<ModeloTipoIncidente,Long>{
    ModeloTipoIncidente findByTipo(String tipo);
}

