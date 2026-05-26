package cl.dgac.incidentes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.dtos.DtoTipoIncidente;
import cl.dgac.incidentes.exepciones.ErrorRecursos;
import cl.dgac.incidentes.mapper.MapperIncidentes;
import cl.dgac.incidentes.model.ModeloIncidentes;
import cl.dgac.incidentes.repository.RepositorioIncidente;

@Service
public class ServicioIncidentes {
    @Autowired
    private RepositorioIncidente repo ;
    public List<DtoIncidente> listaIncidentes(){
        List<DtoIncidente> lista = MapperIncidentes.listasDtoIntancia(repo.findAll());
        if (lista.isEmpty()){
            throw new ErrorRecursos("no existen incidentes");
        }
        return lista;}
    
    public DtoIncidente aadIncidente(DtoIncidente incidente,
        DtoTipoIncidente tipoIncidente){
            repo.save(MapperIncidentes.addModeloIncidente(incidente, tipoIncidente));
            return  MapperIncidentes.modelToDto(MapperIncidentes.addModeloIncidente(incidente, tipoIncidente));
    }
    public DtoIncidente update(Long id , DtoIncidente entity){
        ModeloIncidentes incidente = MapperIncidentes.updateIncidente(id, entity);
        repo.save(incidente);
        return MapperIncidentes.modelToDto(incidente);
    }
    public String delete (DtoIncidente incidente){
        repo.delete(MapperIncidentes.updateIncidente(incidente.Id(),incidente ));
        return "el incidente "+ incidente+ " fue elimiada exitosamente";
    }



}
