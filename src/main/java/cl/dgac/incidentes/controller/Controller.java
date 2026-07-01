package cl.dgac.incidentes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.dgac.incidentes.dtos.DtoIncidente;
import cl.dgac.incidentes.dtos.DtoTipoIncidente;
import cl.dgac.incidentes.exepciones.ErrorRecursos;
import cl.dgac.incidentes.service.ServicioIncidentes;
import cl.dgac.incidentes.service.ServicioTipoIncidente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
//import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
        description = "Obtener Lista de Todos los tipos de incidentes de la BD")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "lista vacia es decir ningun tipo de incidente ")})

    public ResponseEntity<List<DtoTipoIncidente>> listar () {
        return new ResponseEntity<List<DtoTipoIncidente>>(servicio1.listarTipoIncidentes(),HttpStatus.OK);
    }

    @GetMapping("/busacarTiposIncidetes")
    @Operation(
        summary = "obtener tipo de incidente",
        description = "Obten el tipo de incidente BD")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "tipo de incidente inexistente ")})
        
    public ResponseEntity<DtoTipoIncidente>buscador(@RequestParam(name ="tipo") String tipo) {
        return new ResponseEntity<DtoTipoIncidente>(servicio1.buscar(tipo),HttpStatus.OK);
    }
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "400", description = "error en uno o mas valores del json")})
    @PostMapping("/crearTipoIncidente")
    @Operation(
        summary = "crear un tipo de incidente",
        description = "Crea un  el tipo de incidente usando el Json correcto")
    
    public ResponseEntity<DtoTipoIncidente> nuevoTipo (@Valid 
        @RequestBody (
        description = "Datos del tipo de incidente  a crear",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoTipoIncidente.class),
            examples = @ExampleObject(
            name = "Ejemplo de Tipo de Incidente",
            summary = "Descripción del tipo de incidente",
            value = """
            {
                "id": 1,
                "tipo": "Falla de comunicación"
            }
            """
            )
        )) @org.springframework.web.bind.annotation.RequestBody DtoTipoIncidente entity) {
        return new ResponseEntity<DtoTipoIncidente>(servicio1.addTipo(entity),HttpStatus.OK);
    }
    @PutMapping("actualizarTiposIncidentes")
    @Operation(
        summary = "Actualiza un tipo de incidente",
        description = "Actualiza un  el tipo de incidente usando el Tipo incidente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "400", description = "error en uno o mas valores del json")}
    )

    public ResponseEntity<DtoTipoIncidente> actualizar(@RequestParam(name= "tipo") String tipo, 
    @Valid  @RequestBody (
        description = "Datos para actualizar un tipo de incidente",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoTipoIncidente.class),
            examples = @ExampleObject(
            name = "Ejemplo de Tipo de Incidente",
            summary = "Descripción del tipo de incidente",
            value = """
            {
                "id": 1,
                "tipo": "Falla de comunicación"
            }
            """
            )
        )) @org.springframework.web.bind.annotation.RequestBody DtoTipoIncidente entity){
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
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "error ese tiopo de incidente no existe")})
    public ResponseEntity<String> borrar(DtoTipoIncidente entity){
        return new ResponseEntity<String>(servicio1.delete(entity),HttpStatus.OK);
    }
    // parte de incidentes//////////////////////////////////////////DIVICIONNNNNNNNNNNNN 

    @GetMapping("/listarIncidentes")
    @Operation(
        summary = "Muestra Incidentes ",
        description = "Lista todos los incidentes "
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "lista vacia es decir ningun  Incidente reguistrado ")})

    public ResponseEntity<List<DtoIncidente>> listarIncidentes() {
        return new ResponseEntity<List<DtoIncidente>>(servicio2.listaIncidentes(), HttpStatus.OK);
    }
    @GetMapping("/filtradoPorFechas")
    @Operation(
        summary = "Muestra Incidentes por fecha  ",
        description = "Lista todos los incidentes sucedidos entre las fechas "
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "lista vacia es decir ningun  Incidente reguistrado "),
        @ApiResponse(responseCode =  "400", description = "error en el formato de la fecha use YYYY-MM-DD ")
    })

    public ResponseEntity<List<DtoIncidente>> fechaFiltrado(@RequestParam (name = "fechaInicio", required = true) String fechaInicio,
    @RequestParam(name = "fechaFinal", required = true) String fechaFinal ) {
        return new ResponseEntity<List<DtoIncidente>>(servicio2.filtradoFecha(fechaInicio, fechaFinal),HttpStatus.OK);
    }
    
    @PostMapping("/crearIncidente")
    @Operation(
        summary = "crear un incidente",
        description = "Crea un incidente usando el Json correcto"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode =  "400", description = "error en el formato de la fecha use YYYY-MM-DD ")
    })
    public ResponseEntity<DtoIncidente> postMethodName(@Valid  @RequestBody (
        description = "Datos para cear un incidente",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoIncidente.class),
            examples = @ExampleObject(
            name = "Ejemplo de Incidente",
            summary = "Reporte completo de un incidente",
            value = """
                    {
                        "id": 1,
                        "descripcion": "Falla en los sensores de proximidad durante el vuelo de inspección.",
                        "tipo": {
                            "id": 10,
                            "tipo": "Falla Técnica"
                        },
                        "quien": "DroneSolutions S.A.",
                        "fecha_reporte": "2026-06-28T10:30:00",
                        "resuelto": false,
                        "region": "REGIÓN METROPOLITANA"
                    }
                    """
                ) 
            
        )) @org.springframework.web.bind.annotation.RequestBody
        DtoIncidente entity) { 
        String name =entity.tipo().getTipo();
        System.out.println(name);
        if (servicio1.buscar(name) != null){
            DtoTipoIncidente tipo = servicio1.buscar(name);
            return new ResponseEntity<DtoIncidente>(servicio2.aadIncidente(entity),HttpStatus.CREATED);
        }
        throw new ErrorRecursos("tipo no encontrado");
    }

    @PutMapping("/modificarIncidentes")
    public ResponseEntity<DtoIncidente> alctualizarIncidente (@RequestParam(name= "id", required = true ) Long id, 
@RequestBody(
    description = "Datos para modificar un incidente",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoIncidente.class),
            examples = @ExampleObject(
            name = "Ejemplo de Incidente",
            summary = "Reporte completo de un incidente",
            value = """
                    {
                        "id": 1,
                        "descripcion": "Falla en los sensores de proximidad durante el vuelo de inspección.",
                        "tipo": {
                            "id": 10,
                            "tipo": "Falla Técnica"
                        },
                        "quien": "DroneSolutions S.A.",
                        "fecha_reporte": "2026-06-28T10:30:00",
                        "resuelto": false,
                        "region": "REGIÓN METROPOLITANA"
                    }
                    """
                ) 
            
        )) @org.springframework.web.bind.annotation.RequestBody DtoIncidente entity ){
        String name =entity.tipo().getTipo();
        if ((entity.Id().equals(id))|| (entity.equals(null)&& id!= null)){
            if (servicio1.buscar(name) != null){
                DtoTipoIncidente tipo = servicio1.buscar(name);
                return new ResponseEntity<DtoIncidente>(servicio2.update(id, entity),HttpStatus.OK); 
            }
        }
        throw new ErrorRecursos("tenemos ");   
    }
}
/////Crear  nuevas funcioones de put para catualizxar  solo agregale a la de arriba el id;

