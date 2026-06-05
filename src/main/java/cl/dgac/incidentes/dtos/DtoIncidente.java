package cl.dgac.incidentes.dtos;

import java.time.LocalDateTime;

import cl.dgac.incidentes.model.ModeloTipoIncidente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoIncidente(
    Long Id,
    @NotBlank(message = "Devemos tener una discripcion")
    @Size(max = 500 , min=4, message = "la descripcion debe tener entre 3  a 500 caracteres")
    String descripcion,
    ModeloTipoIncidente tipo,
    @NotBlank(message= "no debe estar en blanco")
    @Size(max = 60, min= 3, message = "quien deve tener entre 3 a 60 carcteres" )
    String quien,
    LocalDateTime fecha_reporte,
    boolean resuelto,
    @Size(max = 35, min=4, message = "El nombre de la region deve tener entre 4 a 35 caracteres")
    @NotBlank(message = "la region deve tener un nombre ")
    String region


) {

}
