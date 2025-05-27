package com.example.aula.controller;

import com.example.aula.model.Usuario;
import com.example.aula.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;  // Correção do nome do serviço

    // Construtor para injeção de dependência
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos os usuários
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    // Salvar novo usuário
    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Usuario usuario) {
        try {
            usuarioService.salvar(usuario);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("mensagem", "Usuário cadastrado com sucesso."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao cadastrar usuário: " + e.getMessage()));
        }
    }

    // Atualizar usuário existente
    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Usuario usuario) {
        try {
            usuarioService.atualizar(usuario);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of("mensagem", "Usuário atualizado com sucesso"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao atualizar usuário: " + e.getMessage()));
        }
    }

    // Excluir usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id) {
        try {
            usuarioService.excluir(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of("mensagem", "Usuário excluído com sucesso"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao excluir usuário: " + e.getMessage()));
        }
    }
}