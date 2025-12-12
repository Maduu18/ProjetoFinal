package com.Erp.demo.service;
import com.Erp.demo.exception.EmailDuplicadoException;
import com.Erp.demo.model.Usuario;
import com.Erp.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
        // A. Verificação de Duplicidade (Melhor lançar exceção customizada do que esperar o erro do banco)
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("O e-mail " + usuario.getEmail() + " já está cadastrado.");
        }
        // B. Criptografia: Codifica a senha antes de salvar!
        String senhaCriptografada = PasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        // C. Adicionar campo 'ativo' (Se você adicionar o campo no Model)
        // usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }

    // Listar todos
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com e-mail: " + email));
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        if (!usuarioExistente.getEmail().equals(usuarioAtualizado.getEmail())) {
            if (usuarioRepository.findByEmail(usuarioAtualizado.getEmail()).isPresent()) {
                throw new EmailDuplicadoException("O novo e-mail " + usuarioAtualizado.getEmail() + " já está em uso por outro usuário.");
            }
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        }

        if (usuarioAtualizado.getNome() != null && !usuarioAtualizado.getNome().isBlank()) {
            usuarioExistente.setNome(usuarioAtualizado.getNome());
        }
        if (usuarioAtualizado.getPerfil() != null) {
            usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
        }

        // LÓGICA CRÍTICA DE SENHA:
        // Só criptografa e atualiza se uma nova senha (em texto puro) foi fornecida.
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
            // A senha nova deve ser criptografada!
            String novaSenhaCriptografada = PasswordEncoder.encode(usuarioAtualizado.getSenha());
            usuarioExistente.setSenha(novaSenhaCriptografada);
        }
        // Se a senha for nula/vazia, a senha existente (criptografada) é mantida.
        
        return usuarioRepository.save(usuarioExistente);
    }

    // 3. Deletar usuário (ALTERADO PARA EXCLUSÃO LÓGICA)
    @Transactional
    public void desativarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        
        // Se o usuário tem FKs em Pedido, DELETAR causaria erro.
        // O Service aplica a regra de negócio: apenas inativar o acesso.git p
        usuario.setAtivo(false); 
        
        usuarioRepository.save(usuario);
    }
}
