package cl.dgac.incidentes.mapper;

import java.util.ArrayList;
import java.util.List;

import cl.dgac.incidentes.dtos.DtoTipoIncidente;
import cl.dgac.incidentes.model.ModeloTipoIncidente;

public class MapperTipoIncidente {

    public static ModeloTipoIncidente addModelo(DtoTipoIncidente entity){
        ModeloTipoIncidente modelo = new ModeloTipoIncidente();
        modelo.setId(null);
        modelo.setTipo(entity.tipo());
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
    public static List<DtoTipoIncidente> lsiatasDto (List<ModeloTipoIncidente> lista){
        List<DtoTipoIncidente> dtos = new ArrayList<>();
        for (ModeloTipoIncidente mode : lista){
            dtos.add(modelToDto(mode));
        }
        return dtos;
    }
}
