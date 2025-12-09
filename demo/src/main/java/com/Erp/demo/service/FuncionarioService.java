package com.Erp.demo.service;
import com.Erp.demo.model.Funcionario;
import com.Erp.demo.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    // Criar funcionário
    public Funcionario criarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Listar todos
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Buscar por ID
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + id));
    }

    // Atualizar funcionário
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = buscarPorId(id);

        funcionarioExistente.setUsuario(funcionarioAtualizado.getUsuario());

        return funcionarioRepository.save(funcionarioExistente);
    }

    // Deletar funcionário
    public void deletarFuncionario(Long id) {
        Funcionario funcionario = buscarPorId(id);
        funcionarioRepository.delete(funcionario);
    }
}
