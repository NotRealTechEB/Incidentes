package cl.dgac.incidentes.exepciones;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.dgac.incidentes.dtos.DtoError;
import jakarta.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class ManejosExepcciones {

    @ExceptionHandler(ErrorRecursos.class)
    public ResponseEntity<DtoError> ErrorEnRecursos(ErrorRecursos ex,HttpServletRequest request){
        DtoError error = new DtoError(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "recurso no encontrado",
            ex.getMessage(),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);}



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> exepcionesValicadionDto(MethodArgumentNotValidException ex){
        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String campo=((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo,mensaje);
        });
        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);}
        
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DtoError> manejarDuplicados(DataIntegrityViolationException ex, HttpServletRequest request){
        DtoError error = new DtoError(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            "Error duplicado",
            "El rut ingresado a se encuentra registrado ",
            request.getRequestURI()
        );
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
