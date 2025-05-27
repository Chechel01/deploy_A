package com.example.aula.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exceção para argumentos inválidos (ex: ID inválido)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handlerIllegalArgument(IllegalArgumentException erro) {
        return buildResponse(HttpStatus.BAD_REQUEST, erro.getMessage());
    }

    // Exceção para erros em tempo de execução
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handlerRuntimeException(RuntimeException erro) {
        return buildResponse(HttpStatus.BAD_REQUEST, erro.getMessage());
    }

    // Exceção para erros de validação de argumentos no método (ex: campos obrigatórios não preenchidos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException erro) {
        String mensagemErro = erro.getBindingResult().getFieldErrors().isEmpty() ?
                "Erro de validação desconhecido" :
                erro.getFieldErrors().get(0).getDefaultMessage();
        return buildResponse(HttpStatus.BAD_REQUEST, mensagemErro);
    }

    // Exceção para nome já cadastrado (exemplo de uma exceção customizada)
    @ExceptionHandler(NomeJaCadastradoException.class)
    public ResponseEntity<Map<String, Object>> handlerNomeJaCadastrado(NomeJaCadastradoException erro) {
        return buildResponse(HttpStatus.CONFLICT, erro.getMessage());
    }

    // Exceção para método HTTP não suportado (ex: método GET em um endpoint que espera POST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException erro) {
        return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, "Método HTTP não suportado");
    }

    // Exceção para recurso não encontrado (ex: URL inexistente)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerNoResourceFoundException(NoResourceFoundException erro) {
        return buildResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }

    // Exceção para problemas de leitura de corpo de requisição (ex: JSON mal formatado)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handlerHttpMessageNotReadableException(HttpMessageNotReadableException erro) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Corpo da requisição mal formatado ou ausente");
    }

    // Exceção genérica para erros não capturados explicitamente
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handlerGenericException(Exception erro) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: " + erro.getMessage());
    }

    // Método auxiliar para criar uma resposta padrão
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String mensagem) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("mensagem", mensagem);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.status(status).body(response);
    }
}
