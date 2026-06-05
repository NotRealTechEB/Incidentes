package cl.dgac.incidentes.mapper;

import java.util.ArrayList;
import java.util.List;
import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.model.ModeloIncidentes;

public class MapperIncidentes {
    public static ModeloIncidentes addModeloIncidente(DtoIncidente entity){
        ModeloIncidentes modelo = new ModeloIncidentes();
        modelo.setId(null);
        modelo.setDescripcion(entity.descripcion());
        modelo.setTipo(entity.tipo());
        modelo.setQuien(entity.quien().toUpperCase());
        modelo.setResuelto(entity.resuelto());
        modelo.setRegion(entity.region());
        modelo.setFecha_reporte(entity.fecha_reporte());
        return modelo;
    }

    public static DtoIncidente modelToDto(ModeloIncidentes entity){
        DtoIncidente dto = new DtoIncidente( entity.getId(),entity.getDescripcion(),entity.getTipo(),entity.getQuien(),
        entity.getFecha_reporte(),false,entity.getRegion());
        return dto;
    }
    
    public static ModeloIncidentes resuelto(Long id, DtoIncidente entity){
        ModeloIncidentes modelo = addModeloIncidente(entity);
        modelo.setId(id);
        modelo.setResuelto(true);
        return modelo;
    }

    public static List<DtoIncidente> listasDtoIntancia (List<ModeloIncidentes> lista){
        List<DtoIncidente> dtos = new ArrayList<>();
        for (ModeloIncidentes mode : lista){
            dtos.add(modelToDto(mode));
        }
        return dtos;
    }

}
