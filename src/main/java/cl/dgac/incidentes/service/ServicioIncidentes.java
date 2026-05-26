package cl.dgac.incidentes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.exepciones.ErrorRecursos;
import cl.dgac.incidentes.mapper.MapperIncidentes;
import cl.dgac.incidentes.repository.RepositorioIncidente;

@Service
public class ServicioIncidentes {
    @Autowired
    private RepositorioIncidente repo ;
    public List<DtoIncidente> Listaincidentes(){
        List<DtoIncidente> lista = MapperIncidentes.listasDtoIntancia(repo.findAll());
        if (lista.isEmpty()){
            throw new ErrorRecursos("no existen incidentes");
        }
        return lista;
    }

}
