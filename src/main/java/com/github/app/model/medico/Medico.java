package com.github.app.model.medico;

import com.github.app.model.endereco.Endereco;

import jakarta.persistence.*; //já importam tudo que está abaixo
import lombok.*;

// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.EqualsAndHashCode;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

//Classe modelo responsável por criar uma tabela e suas colunas no BD.

@Getter //Lombok - cria get para todos os atributos
@Setter //Lombok - cria set para todos os atributos
@AllArgsConstructor // Lombok - cria um construtor com todos os atributos
@NoArgsConstructor  //Lombok - cria um construtor sem atributos
@EqualsAndHashCode(of = "id")  //Lombok - cria uma lógica de comparação através do campo 'id'
@Entity //SPRING JPA - informa que a classe abaixo é uma entidade, ou seja, será uma tabela no BD
@Table(name = "medicos") //SPRING JPA - (opcional) - gera uma tabela com nome médicos no BD
public class Medico {

    @Id //SPRING JPA - informa para o BD que a chave primária é o id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //SPRING JPA - cria o id único de forma automática
    private Integer id; // id não está vindo do insomnia. Integer = classe wrapper = classes que "embrulham" (wrap) tipos primitivos (int, char, double, etc.) em objetos, permitindo utilizá-los onde objetos são necessários. Elas fornecem métodos úteis para conversão de tipos (ex: Integer.parseInt()) e permitem que valores sejam null. 

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo = true;
    
    @Enumerated(EnumType.STRING) //SPRING JPA - informa para o BD que o atributo é do tipo enum
    private Especialidade especialidade;

    @Embedded //Utilizada na classe pai. Associa uma entidade a uma tabela auxiliar. Usar no atributo da entidade principal que referencia a classe @Embeddable(Nesse caso, a class Endereco)
    private Endereco endereco;
    
    //Constructor com o recebimento dos dados convertendo para objeto
    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome(); //() => para diferenciar de um construtor que está tendo uma conversão de uma que não está tendo
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();      
        this.endereco = new Endereco(dados.endereco());
    }

    //Método para verificar a atualização do médico, recebendo um objeto do tipo DadosAtualizacaoMedico e atualizando os atributos do médico com os dados recebidos na requisição.
    public void atualizarInformacoes(DadosAtualizacaoMedico dados){
        //Verifica se o nome recebido é diferente de null, ou seja, se o nome foi enviado na requisição da atualização (PUT), e se for diferente de null, atualiza o nome do médico com o novo nome recebido
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if (dados.email() != null){
            this.email = dados.email();
        }


        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    //Método responsável por alterar o status do médico de true para false
    public void exclusaoLogica(){
        this.ativo = false;
    }
}
