package cl.dgac.incidentes.dtos;

import java.time.LocalDateTime;

import cl.dgac.incidentes.model.ModeloTipoIncidente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoIncidente(
    Long Id,
    @Schema(description = "descripcion del incidente")
    @NotBlank(message = "Devemos tener una discripcion")
    @Size(max = 500 , min=4, message = "la descripcion debe tener entre 3  a 500 caracteres")
    String descripcion,
    @Schema(description = "tipo de incidente relacionado")
    ModeloTipoIncidente tipo,
    @Schema(description = "nombre de la Empresa que genero el incidente")
    @NotBlank(message= "no debe estar en blanco")
    @Size(max = 60, min= 3, message = "quien deve tener entre 3 a 60 carcteres" )
    String quien,
    @Schema(description = "fecha del incidente ")
    LocalDateTime fecha_reporte,
    @Schema(description = "estado del incidente")
    boolean resuelto,
    @Schema(description = "el nombre de la region donde se produjo el incidente")
    @Size(max = 35, min=4, message = "El nombre de la region deve tener entre 4 a 35 caracteres")
    @NotBlank(message = "la region deve tener un nombre ")
    String region
) {

}
