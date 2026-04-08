package com.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*; //já importa todos os que estão comentados abaixo

import com.github.app.model.medico.DadosAtualizacaoMedico;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.github.app.model.medico.DadosCadastroMedico;
import com.github.app.model.medico.DadosListagemMedico;
import com.github.app.model.medico.Medico;
import com.github.app.model.medico.MedicoRepository;

import jakarta.transaction.Transactional;

@RestController // SPRING WEB - Informa para o Springboot que abaixo é uma classe controladora
                // de requisições (GET-POST-PUT-DELETE)
@RequestMapping("/medicos") // SPRING WEB - Cria um caminho (endpoint) para a classe MedicoCotroller
public class MedicoController {

    @Autowired // Sobreescrevendo algo. É um padrão utilizado na injeção de dependência.
    private MedicoRepository repository; // tem que importar

    @PostMapping // SPRING WEB - Informa que o método abaixo é do tipo POST (cadastrar)
    @Transactional // SPRING DATA JPA - Informa para o Springboot que o método abaixo é uma transação, ou seja, que as operações realizadas dentro do método devem ser tratadas como uma única unidade de trabalho, garantindo a integridade dos dados e a consistência do banco de dados.
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        repository.save(new Medico(dados));

    }

    @GetMapping("todos") // SRPING WEB - Informa que o método abaixo é do tipo GET (buscar/ler)
    public List<Medico> listarTodos() { // List tem que importar java.util.List
        return repository.findAll();
    }

    @GetMapping("listar") 
    public List<DadosListagemMedico> listar() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();  //mapeando o que está em DadosListagemMedico e convertendo para uma lista
        // findAll() -> método que retorna uma lista de objetos do tipo DadosListagemMedico
        // stream() -> método utilizado para transformar a lista em um fluxo de daodos, permitindo aplicar operações de transformação
        // map(DadosListagemMedico::new) -> método utilizado para converter cada objeto do tipo medico em json DadosListagemMedico, utilzando o constructor que criamos em DadosListagemMedico
        // toList() -> método utilizado para coletar os resultados em uma nova lista do tipo DadosListagemMedico, que é o formato que queremos retornar para a API
    }

    @GetMapping
    public Page<DadosListagemMedico> listarPorPagina(Pageable paginacao) { //Page tem que importar o do domain (org.springframework.data.domain.Page). Pageable também.
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
        //return repository.findAll().stream().map(DadosListagemMedico::new).toList(); 
    }

    @PutMapping
    @Transactional // SPRING DATA JPA - Informa para o Springboot que o método abaixo é uma transação, ou seja, que as operações realizadas dentro do método devem ser tratadas como uma única unidade de trabalho, garantindo a integridade dos dados e a consistência do banco de dados.
    public void atualizar(@RequestBody DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        //var é uma palavra reservada do java para declarar uma variável sem especificar seu tipo: o tipo da variável é inferido pelo complidaor com base no valor que foi atribuido a ela.
        medico.atualizarInformacoes(dados);
    }



}
