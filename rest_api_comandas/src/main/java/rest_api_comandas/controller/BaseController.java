package rest_api_comandas.controller;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import rest_api_comandas.errors.ValidationError;

public abstract class BaseController {

    protected <T> ResponseEntity<?> executar(Supplier<T> funcao) {
        try {
            return ResponseEntity.ok(funcao.get());
        } catch (ValidationError e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Houve um erro interno no sistema");
        }
    }
}