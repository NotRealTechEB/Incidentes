package cl.dgac.incidentes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.dtos.DtoTipoIncidente;
import cl.dgac.incidentes.exepciones.ErrorRecursos;
import cl.dgac.incidentes.mapper.MapperTipoIncidente;
import cl.dgac.incidentes.service.ServicioIncidentes;
import cl.dgac.incidentes.service.ServicioTipoIncidente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1.0/Incidentes")
@Tag(name = "Incidente y tipo de incidente",
    description = "esta api se encarga de comunicarse con la BD para hacer el crud basico y flitros de busqueda"
)
public class Controller {
    private final ServicioTipoIncidente servicio1;
    private final ServicioIncidentes servicio2;
    public Controller(ServicioTipoIncidente servicio1, ServicioIncidentes servicio2){
        this.servicio1=servicio1;
        this.servicio2= servicio2; 
    }
    @GetMapping("/listarTiposIncidentes")
    @Operation(
        summary = "obtener tipos de inicidente",
        description = "Obtener Lista de Todos los tipos de incidentes de la BD"
    )
    public ResponseEntity<List<DtoTipoIncidente>> listar () {
        return new ResponseEntity<List<DtoTipoIncidente>>(servicio1.listarTipoIncidentes(),HttpStatus.OK);
    }

    @GetMapping("/busacarTiposIncidetes")
    @Operation(
        summary = "obtener tipo de incidente",
        description = "Obten el tipo de incidente BD"
    )
    public ResponseEntity<DtoTipoIncidente>buscador(@RequestParam(name ="tipo") String tipo) {
        return new ResponseEntity<DtoTipoIncidente>(servicio1.buscar(tipo),HttpStatus.OK);
    }

    @PostMapping("/crearTipoIncidente")
    @Operation(
        summary = "crear un tipo de incidente",
        description = "Crea un  el tipo de incidente usando el Json correcto"
    )
    public ResponseEntity<DtoTipoIncidente> nuevoTipo (@Valid@RequestBody DtoTipoIncidente entity) {
        return new ResponseEntity<DtoTipoIncidente>(servicio1.addTipo(entity),HttpStatus.OK);
    }
    @PutMapping("actualizarTiposIncidentes")
    @Operation(
        summary = "Actualiza un tipo de incidente",
        description = "Actualiza un  el tipo de incidente usando el Tipo incidente"
    )
    public ResponseEntity<DtoTipoIncidente> actualizar(@RequestParam(name= "tipo") String tipo, 
    @Valid @RequestBody DtoTipoIncidente entity){
        return new ResponseEntity<DtoTipoIncidente>(servicio1.
        updateTipo(
        servicio1.buscar(tipo).id()
        ,entity),HttpStatus.OK);
    }
    @DeleteMapping("/eliminarTipo")
    @Operation(
        summary = "Elimina  un tipo de incidente",
        description = "Elimina un tipo de incidente usando el json correto"
    )
    public ResponseEntity<String> borrar(DtoTipoIncidente entity){
        return new ResponseEntity<String>(servicio1.delete(entity),HttpStatus.OK);
    }
    // parte de incidentes 
    @GetMapping("/listarIncidentes")
    @Operation(
        summary = "Muestra Incidentes ",
        description = "Lista todos los incidentes "
    )
    public ResponseEntity<List<DtoIncidente>> listarIncidentes() {
        return new ResponseEntity<List<DtoIncidente>>(servicio2.listaIncidentes(), HttpStatus.OK);
    }
    @GetMapping("/filtradoPorFechas")
    @Operation(
        summary = "Muestra Incidentes por fecha  ",
        description = "Lista todos los incidentes sucedidos entre las fechas "
    )
    public ResponseEntity<List<DtoIncidente>> fechaFiltrado(@RequestParam (name = "fechaInicio", required = true) String fechaInicio,
    @RequestParam(name = "fechaFinal", required = true) String fechaFinal ) {
        return new ResponseEntity<List<DtoIncidente>>(servicio2.filtradoFecha(fechaInicio, fechaFinal),HttpStatus.OK);
    }
    
    @PostMapping("/crearIncidente")
    @Operation(
        summary = "crear un incidente",
        description = "Crea un incidente usando el Json correcto"
    )
    public ResponseEntity<DtoIncidente> postMethodName(@Valid @RequestBody DtoIncidente entity) { 
        String name =entity.tipo().getTipo();
        System.out.println(name);
        if (servicio1.buscar(name) != null){
            DtoTipoIncidente tipo = servicio1.buscar(name);
            DtoIncidente incidente= new DtoIncidente(entity.Id(), entity.descripcion(),
            MapperTipoIncidente.update(tipo.id(),tipo), entity.quien(), entity.fecha_reporte(), false, entity.region());
            servicio2.aadIncidente(incidente);
            return new ResponseEntity<DtoIncidente>(incidente,HttpStatus.CREATED);
        }
        throw new ErrorRecursos("tipo no encontrado");
    }
    
}
