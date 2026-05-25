package cl.dgac.incidentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.dgac.incidentes.model.ModeloIncidentes;

@Repository
public interface RepositorioIncidente extends JpaRepository<ModeloIncidentes,Long> {
    

}
