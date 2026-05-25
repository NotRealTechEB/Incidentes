package cl.dgac.incidentes.mapper;

import java.util.ArrayList;
import java.util.List;

import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.dtos.DtoTipoIncidente;
import cl.dgac.incidentes.model.ModeloIncidentes;
import cl.dgac.incidentes.model.ModeloTipoIncidente;

public class MapperTipoIncidente {

    public static ModeloTipoIncidente addModelo(DtoTipoIncidente entity){
        ModeloTipoIncidente modelo = new ModeloTipoIncidente();
        modelo.setId(null);
        modelo.setTipo(entity.tipo().toUpperCase());
        return modelo;
    }
    
    public static ModeloTipoIncidente update(Long id, DtoTipoIncidente entity){
        ModeloTipoIncidente modelo = addModelo(entity);
        modelo.setId(id);
        return modelo;
    }
    public static DtoTipoIncidente modelToDto(ModeloTipoIncidente entity){
        DtoTipoIncidente dto = new DtoTipoIncidente( entity.getTipo() ,entity.getId());
        return dto;
    }
    public static List<DtoTipoIncidente> listasDto (List<ModeloTipoIncidente> lista){
        List<DtoTipoIncidente> dtos = new ArrayList<>();
        for (ModeloTipoIncidente mode : lista){
            dtos.add(modelToDto(mode));
        }
        return dtos;
    }
    public static ModeloIncidentes addModeloIncidente(DtoIncidente entity, DtoTipoIncidente tipo){
        ModeloIncidentes modelo = new ModeloIncidentes();
        modelo.setId(null);
        modelo.setDescripcion(entity.descripcion());
        modelo.setTipo(update(tipo.id(), tipo));
        modelo.setQuien(entity.quien());
        return modelo;
    }
    
    public static ModeloIncidentes updateIncidente(Long id, DtoIncidente entity){
        ModeloIncidentes modelo = addModeloIncidente(entity,modelToDto(entity.tipo()));

        modelo.setId(id);
        return modelo;
    }

    public static List<DtoTipoIncidente> lsiatasDto (List<ModeloTipoIncidente> lista){
        List<DtoTipoIncidente> dtos = new ArrayList<>();
        for (ModeloTipoIncidente mode : lista){
            dtos.add(modelToDto(mode));
        }
        return dtos;
    }

    
}
