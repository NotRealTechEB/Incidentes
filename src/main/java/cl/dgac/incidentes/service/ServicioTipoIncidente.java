package cl.dgac.incidentes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.dgac.incidentes.dtos.DtoTipoIncidente;
import cl.dgac.incidentes.exepciones.ErrorRecursos;
import cl.dgac.incidentes.mapper.MapperTipoIncidente;
import cl.dgac.incidentes.model.ModeloTipoIncidente;
import cl.dgac.incidentes.repository.RepositorioTipoIncidente;

@Service
public class ServicioTipoIncidente {
    @Autowired
    private RepositorioTipoIncidente repo;

    public List<DtoTipoIncidente> listarTipoIncidentes(){
        List<DtoTipoIncidente> lista = MapperTipoIncidente.listasDto(repo.findAll());
        if (lista.isEmpty()){
            throw new ErrorRecursos("no existen tipos de incidentes ");
        }
        return lista ;
    }
    public  DtoTipoIncidente addTipo(DtoTipoIncidente entity){
        ModeloTipoIncidente modelo=repo.save(MapperTipoIncidente.addModelo(entity));
        return(MapperTipoIncidente.modelToDto(modelo));
    }
    public DtoTipoIncidente buscar (String tipo){
        if (repo.findByTipo(tipo.toUpperCase()) == null){
            return null;
        }
        return  MapperTipoIncidente.modelToDto(repo.findByTipo(tipo.toUpperCase()));
    }
    public  DtoTipoIncidente updateTipo(Long Id,DtoTipoIncidente entity){
        ModeloTipoIncidente modelo=repo.save(MapperTipoIncidente.update(Id,entity));
        return(MapperTipoIncidente.modelToDto(modelo));}
    
    public String delete(DtoTipoIncidente tipo){
        repo.delete(MapperTipoIncidente.update(buscar(tipo.tipo()).id(), tipo));
        return "el tipo de incidente "+ tipo +" fue eliminado exitosamente";
    }
}
