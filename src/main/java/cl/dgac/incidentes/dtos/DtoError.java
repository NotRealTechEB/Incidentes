package cl.dgac.incidentes.dtos;

import java.time.LocalDateTime;

public record DtoError(
    LocalDateTime fecha,
    int codigoHttp,
    String error,
    String mensaje,
    String ruta
) {

}
