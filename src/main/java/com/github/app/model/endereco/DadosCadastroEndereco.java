package com.github.app.model.endereco;

import jakarta.validation.constraints.NotBlank;

//Classe para ser usada tanto em médicos quanto em pacientes. Para não precisar repetir nos dois.

//Classe DTO
public record DadosCadastroEndereco(

    @NotBlank
    String logradouro,

    @NotBlank
    String bairro,

    @NotBlank
    String cep,

    String complemento,

    @NotBlank
    String cidade,

    @NotBlank
    String uf
) {
    
}
