package com.github.app.model.medico;

import com.github.app.model.endereco.DadosCadastroEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Essa classe é responsável por pegar os dados que estão vindo do simulador de requisição (insomnia) e transformar em atributos (variáveis) de uma entidade chamada Médico.

//A classe do tipo record já cria todos os getters, setters, constructors, hashcode e equals.


public record DadosCadastroMedico(

    @NotBlank //verifica se o campo está preenchido -> só consegue utulizar em Strings
    String nome, 

    @Email //verifica se é um e-mail válido
    @NotBlank
    String email, 

    String telefone,

    String crm, 

    @NotNull //não é possível usar NotBlank porque não é string
    Especialidade especialidade,  //uma classe só para especialidades, onde deixo pré definido quais são as especialidades aceitas pelo sistema

    @NotNull @Valid  //Valid ativa mecanismo de validação
    DadosCadastroEndereco endereco  //uma classe para endereço, pois são informações que irão se repetir também para pacientes
    ) {
    
}
