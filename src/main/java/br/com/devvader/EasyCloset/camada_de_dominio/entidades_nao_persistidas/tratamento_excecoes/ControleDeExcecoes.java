package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class ControleDeExcecoes {

    @Autowired
    private MessageSource mensagemInter;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> excecaoMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ApiDeErrosDeBeanValidation> listaDeErrosDeBeanValidations = new ArrayList<>();

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        fieldErrors.forEach(erro -> {
            String mensagem = mensagemInter.getMessage(erro, LocaleContextHolder.getLocale());
            ApiDeErrosDeBeanValidation erroDeBeanValidation = new ApiDeErrosDeBeanValidation(
                    HttpStatus.BAD_REQUEST.toString(), mensagem, erro.getCode(), erro.getField());
            listaDeErrosDeBeanValidations.add(erroDeBeanValidation);
        });

        return ResponseEntity.badRequest().body(listaDeErrosDeBeanValidations.get(0));
    }
}
