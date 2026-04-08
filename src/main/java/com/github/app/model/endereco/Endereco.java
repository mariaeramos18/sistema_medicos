package com.github.app.model.endereco;

//Nâo é uma tabela separada no banco de dados. Vai estar sempre relacionada ao médico ou ao paciente.

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable  //Usar na classe que será incorporada. Indica ao JPA que esta classe não é uma entidade por si só (não tem ID próprio), mas pode ser incorporada em outras classes.
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String complemento;
    private String cidade;
    private String uf;

    //Constructor recebendo os dados convertidos DTO (Data Transfer Object). No Spring Boot é um padrão de projeto usado para transferir dados entre camadas (controller/serviço) e entre o backend e frontend
    public Endereco (DadosCadastroEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }

    //Método para atualizar as informações do endereco,recebendo um objeto do tipo DadosCadastroEndereco e atualizando os atributosde endereco com os dados recebidos.

    public void atualizarInformacoes(DadosCadastroEndereco dados){
        if(dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        }
        if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }
        if(dados.cep() != null){
            this.cep = dados.cep();
        }
        if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.uf() != null){
            this.uf = dados.uf();
        }
    }
}
