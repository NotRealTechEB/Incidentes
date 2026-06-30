package cl.dgac.incidentes.service;

import java.util.ArrayList;
import java.util.Arrays;
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
     private final ArrayList<String>  regiones = new ArrayList<>(Arrays.asList(
            "REGIÓN DE ARICA Y PARINACOTA",
            "REGIÓN DE TARAPACÁ",
            "REGIÓN DE ANTOFAGASTA",
            "REGIÓN DE ATACAMA",
            "REGIÓN DE COQUIMBO",
            "REGIÓN DE VALPARAÍSO",
            "REGIÓN METROPOLITANA DE SANTIAGO",
            "REGIÓN DEL LIBERTADOR GENERAL BERNARDO O'HIGGINS",
            "REGIÓN DEL MAULE",
            "REGIÓN DE ÑUBLE",
            "REGIÓN DEL BÍO BÍO",
            "REGIÓN DE LA ARAUCANÍA",
            "REGIÓN DE LOS RÍOS",
            "REGIÓN DE LOS LAGOS",
            "REGIÓN DE AYSÉN DEL GENERAL CARLOS IBÁÑEZ DEL CAMPO",
            "REGIÓN DE MAGALLANES Y DE LA ANTÁRTICA CHILENA"
        ));
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
        for (String i : regiones){
            if (i.equals(incidente.region())){
                repo.save(MapperIncidentes.addModeloIncidente(incidente));
                return incidente;
            }
        }
        throw new ErrorRecursos("region no existe o tiene ele formato icnortrecto el formato es REGIÓN DE ARICA Y PARINACOTA ");
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
