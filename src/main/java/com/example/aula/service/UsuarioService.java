package com.example.aula.service;

import com.example.aula.exception.NomeJaCadastradoException;
import com.example.aula.model.Usuario;
import com.example.aula.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Construtor para injeção de dependência
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Salvar novo usuário
    public Usuario salvar(@Valid Usuario usuario) {
        // Aqui você pode validar se o e-mail já está cadastrado, por exemplo:
        // if (usuarioRepository.existsByEmail(usuario.getEmail())) {
        //     throw new EmailJaCadastradoException("E-mail já cadastrado.");
        // }
        return usuarioRepository.save(usuario);
    }

    // Atualizar um usuário existente
    public Usuario atualizar(@Valid Usuario usuario) {
        // Buscar o usuário pelo ID
        Usuario usuarioAtualizar = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Atualizar os dados do usuário
        usuarioAtualizar.setNome(usuario.getNome());
        usuarioAtualizar.setIdade(usuario.getIdade());
        usuarioAtualizar.setAltura(usuario.getAltura());
        usuarioAtualizar.setPeso(usuario.getPeso());
        usuarioAtualizar.setPosicao(usuario.getPosicao());
        usuarioAtualizar.setNumCamisa(usuario.getNumCamisa());

        // Salvar as alterações
        return usuarioRepository.save(usuarioAtualizar);
    }

    // Excluir um usuário pelo ID
    public void excluir(Long id) {
        Usuario usuarioExcluir = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuarioRepository.deleteById(usuarioExcluir.getId());
    }
}
