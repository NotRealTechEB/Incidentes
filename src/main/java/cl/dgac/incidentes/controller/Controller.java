package cl.dgac.incidentes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.dtos.DtoTipoIncidente;
import cl.dgac.incidentes.service.ServicioIncidentes;
import cl.dgac.incidentes.service.ServicioTipoIncidente;
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
@RequestMapping("api/v1.0/Incidentes")
public class Controller {
    private final ServicioTipoIncidente servicio1;
    private final ServicioIncidentes servicio2;
    public Controller(ServicioTipoIncidente servicio1, ServicioIncidentes servicio2){
        this.servicio1=servicio1;
        this.servicio2= servicio2; 
    }
    @GetMapping("/listarTiposIncidentes")
    public ResponseEntity<List<DtoTipoIncidente>> listar () {
        return new ResponseEntity<List<DtoTipoIncidente>>(servicio1.listarTipoIncidentes(),HttpStatus.OK);
    }

    @GetMapping("/busacarTiposIncidetes")
    public ResponseEntity<DtoTipoIncidente>buscador(@RequestParam(name ="tipo") String tipo) {
        return new ResponseEntity<DtoTipoIncidente>(servicio1.buscar(tipo),HttpStatus.OK);
    }

    @PostMapping("/crearTipoIncidente")
    public ResponseEntity<DtoTipoIncidente> nuevoTipo (@Valid@RequestBody DtoTipoIncidente entity) {
        return new ResponseEntity<DtoTipoIncidente>(servicio1.addTipo(entity),HttpStatus.OK);
    }
    @PutMapping("actualizarTiposIncidentes")
    public ResponseEntity<DtoTipoIncidente> actualizar(@RequestParam(name= "tipo") String tipo, 
    @Valid @RequestBody DtoTipoIncidente entity){
        return new ResponseEntity<DtoTipoIncidente>(servicio1.
        updateTipo(
        servicio1.buscar(tipo).id()
        ,entity),HttpStatus.OK);
    }
    @DeleteMapping("/eliminarTipo")
    public ResponseEntity<String> borrar(DtoTipoIncidente entity){
        return new ResponseEntity<String>(servicio1.delete(entity),HttpStatus.OK);
    }
    // parte de incidentes 
    @GetMapping("/listarIncidentes")
    public ResponseEntity<List<DtoIncidente>> listarIncidentes() {
        return new ResponseEntity<List<DtoIncidente>>(servicio2.listaIncidentes(), HttpStatus.OK);
    }
    @GetMapping("/filtradoPorFechas")
    public ResponseEntity<List<DtoIncidente>> fechaFiltrado(@RequestParam (name = "fechaInicio", required = true) String fechaInicio,
    @RequestParam(name = "fechaFinal", required = true) String fechaFinal ) {
        return new ResponseEntity<List<DtoIncidente>>(servicio2.filtradoFecha(fechaInicio, fechaFinal),HttpStatus.OK);
    }
    
    @PostMapping("/crearIncidente")
    public ResponseEntity<DtoIncidente> postMethodName(@Valid @RequestBody DtoIncidente entity) {        
        return  new ResponseEntity<DtoIncidente>(servicio2.aadIncidente(entity, servicio1.buscar(entity.tipo().getTipo())),
        HttpStatus.OK);
    }
    
    
}
