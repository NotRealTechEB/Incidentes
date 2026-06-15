package cl.dgac.incidentes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoTipoIncidente(
    @Schema(description = "tipo deinicdente (el nombre)")
    @NotBlank(message = "debe tener un tipo escrito") @Size(max = 60, min= 3,message = "deve tener entre 3 a 60 caracteres") 
    String tipo, 
    Long id
) {
    
}
