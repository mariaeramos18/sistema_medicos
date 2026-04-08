package com.github.app.model.medico;

import com.github.app.model.endereco.DadosCadastroEndereco;

//Classe reponsável por receber os dados de atualização do médico ,utilizando o recurso record do java para criar uma classe imutável, ou seja, os dados não podem ser alterados depois de criados, garantindo a segurança dos dados e a integridade da aplicação.
//DadosCadastroEndereco é um DTO, então com o endereco pode ser alterado em qualquer campo
//estamos reusando (reuso) para a alteração do médico
public record DadosAtualizacaoMedico(Integer id, String nome, String email, DadosCadastroEndereco endereco) {
    
}
