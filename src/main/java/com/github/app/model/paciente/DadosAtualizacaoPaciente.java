package com.github.app.model.paciente;

public record DadosAtualizacaoPaciente(
    Integer id,
    String nome,
    String cpf,
    String email,
    String telefone
) {
    
}
