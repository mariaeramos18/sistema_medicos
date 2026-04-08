package com.github.app.model.paciente;

//import com.github.app.model.endereco.DadosCadastroEndereco;

public record DadosCadastroPaciente(
    String nome,
    String cpf,
    String email,
    String telefone
    //DadosCadastroEndereco endereco
) {
    
}
