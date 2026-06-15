package cl.dgac.incidentes.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.exepciones.ErrorRecursos;
import cl.dgac.incidentes.mapper.MapperIncidentes;
import cl.dgac.incidentes.model.ModeloIncidentes;
import cl.dgac.incidentes.repository.RepositorioIncidente;
import cl.dgac.incidentes.validacion.validacionFormatofecha;

@Service
public class ServicioIncidentes {
    
    private final RepositorioIncidente repo ;
    public ServicioIncidentes (RepositorioIncidente repo){
        this.repo=repo;
    }
    public List<DtoIncidente> listaIncidentes(){
        List<DtoIncidente> lista = MapperIncidentes.listasDtoIntancia(repo.findAll());
        if (lista.isEmpty()){
            throw new ErrorRecursos("no existen incidentes");
        }
        return lista;}
    
    public DtoIncidente aadIncidente(DtoIncidente incidente) {
        DtoIncidente guardado = new DtoIncidente(
            incidente.Id(),incidente.descripcion(),incidente.tipo(),incidente.quien()
            ,incidente.fecha_reporte(),incidente.resuelto(),incidente.region()
        );
        repo.save(MapperIncidentes.addModeloIncidente(guardado));
        return guardado;
    }
    public DtoIncidente update(Long id , DtoIncidente entity){
        ModeloIncidentes incidente = MapperIncidentes.resuelto(id, entity);
        repo.save(incidente);
        return MapperIncidentes.modelToDto(incidente);
    }
    public String delete (DtoIncidente incidente){
        repo.delete(MapperIncidentes.resuelto(incidente.Id(),incidente ));
        return "el incidente "+ incidente+ " fue elimiada exitosamente";
    }
    public List<DtoIncidente> filtradoFecha(String f1, String f2){
        List<DtoIncidente>lista = MapperIncidentes.listasDtoIntancia(
        repo.filtrarPorRangoDeFechas(
        validacionFormatofecha.formatoValido(f1),
        validacionFormatofecha.formatoValido(f2)));
        if (lista.isEmpty()){
            throw new ErrorRecursos("Sindatos  en ese periodo de fechas");
        }
        return lista;
    }



}
