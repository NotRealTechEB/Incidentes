package cl.dgac.incidentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cl.dgac.incidentes.model.ModeloIncidentes;
import java.util.List;

public interface RepositorioIncidente extends JpaRepository<ModeloIncidentes,Long> {
    
    @Query(
        value = "SELECT * FROM incidentes WHERE fecha_reporte BETWEEN " +
                "TO_TIMESTAMP(:fechaInicio ||' 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND " +
                "TO_TIMESTAMP(:fechaFin||' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')", 
        nativeQuery = true)

    List<ModeloIncidentes> filtrarPorRangoDeFechas(
        @Param("fechaInicio") String fechaInicio, 
        @Param("fechaFin") String fechaFin
    );



}
