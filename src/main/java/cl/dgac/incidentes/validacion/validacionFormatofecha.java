package cl.dgac.incidentes.validacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import cl.dgac.incidentes.exepciones.ErrorRecursos;

public class validacionFormatofecha {

    public static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("uuuu-MM-dd")
    .withResolverStyle(ResolverStyle.STRICT);

    public static String formatoValido(String fecha){
        if (fecha==null || fecha.trim().isEmpty()){
            throw new ErrorRecursos("fecha "+ fecha +" esta vacia" );
        }
        try {
            LocalDate.parse(fecha.trim(),formatoFecha);
            return fecha.trim();
        } catch (Exception e) {
            throw new ErrorRecursos("Error de parseo. El texto recibido fue: [" + fecha + "]. Detalle técnico: " + e.getMessage());
        }
    }


}
