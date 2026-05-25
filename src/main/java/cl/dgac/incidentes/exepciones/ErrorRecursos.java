package cl.dgac.incidentes.exepciones;

public class ErrorRecursos extends RuntimeException {
    public ErrorRecursos (String mensaje){
        super(mensaje);
    }
}
