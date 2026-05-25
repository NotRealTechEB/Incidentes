package cl.dgac.incidentes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoTipoIncidente(
    @NotBlank(message = "debe tener un tipo escrito") @Size(max = 60, min= 3,message = "deve tener entre 3 a 60 caracteres") String tipo, 
    Long id
) {
    
}
