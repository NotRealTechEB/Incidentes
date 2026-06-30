package cl.dgac.incidentes.exepciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.dgac.incidentes.dtos.DtoExeption;
import jakarta.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class ManejosExepcciones {
    private ResponseEntity<DtoExeption> buildResponse(
        HttpStatus status,
        String mnesaje,
        String ruta,
        Map<String, String> detalles){
        DtoExeption dto = new DtoExeption(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            mnesaje,
            detalles,
            ruta
        );
        return  ResponseEntity.status(status).body(dto);
    }

    @ExceptionHandler(ErrorRecursos.class)
    public ResponseEntity<DtoExeption> ErrorEnRecursos(ErrorRecursos ex,HttpServletRequest request){
        return buildResponse(HttpStatus.NOT_FOUND,"recurso no encontrado", request.getRequestURI(), null);}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoExeption> exepcionesValicadionDto(MethodArgumentNotValidException ex,HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String campo=((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo,mensaje);
        });
        return buildResponse(HttpStatus.BAD_REQUEST, 
            "elementos en el json tienen problemas", 
            request.getRequestURI(), errores);}
        
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DtoExeption> manejarDuplicados(DataIntegrityViolationException ex, HttpServletRequest request){
        return buildResponse(HttpStatus.CONFLICT, "elemento duplicado ", request.getRequestURI(), null);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DtoExeption> interlServErerror(Exception ex, HttpServletRequest request){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,"ocurrio un   error inesperado " + ex.getMessage(),request.getRequestURI(), null);
        }
    @ExceptionHandler(DateTimeParseException.class) 
    public ResponseEntity<DtoExeption> manejarErrorFecha(Exception ex, HttpServletRequest request){
    return buildResponse(HttpStatus.BAD_REQUEST, "El formato de fecha es inválido. Use YYYY-MM-DD", request.getRequestURI(), null);
}
}
