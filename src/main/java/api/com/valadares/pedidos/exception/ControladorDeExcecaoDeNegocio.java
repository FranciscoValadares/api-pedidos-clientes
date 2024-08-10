package api.com.valadares.pedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControladorDeExcecaoDeNegocio{

    @ExceptionHandler(ExcecaoDeNegocio.class)
    public ResponseEntity<RespostaDeErro> tratarExcecaoDeNegocio(ExcecaoDeNegocio ex) {
        RespostaDeErro mensagemDeErro = new RespostaDeErro("ERRO_DE_NEGOCIO", ex.getMessage());
        return new ResponseEntity<>(mensagemDeErro, HttpStatus.UNPROCESSABLE_ENTITY);   
    }
}

