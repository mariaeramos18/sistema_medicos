package com.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.github.app.model.paciente.DadosAtualizacaoPaciente;
import com.github.app.model.paciente.DadosCadastroPaciente;
import com.github.app.model.paciente.DadosListagemPaciente;
import com.github.app.model.paciente.Paciente;
import com.github.app.model.paciente.PacienteRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;
    
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping("todos") // SRPING WEB - Informa que o método abaixo é do tipo GET (buscar/ler)
    public List<Paciente> listarTodos() { // List tem que importar java.util.List
        return repository.findAll();
    }

    @GetMapping("listar") 
    public List<DadosListagemPaciente> listar() {
        return repository.findAll().stream().map(DadosListagemPaciente::new).toList();  //mapeando o que está em DadosListagemPaciente e convertendo para uma lista
        // findAll() -> método que retorna uma lista de objetos do tipo DadosListagemPaciente
        // stream() -> método utilizado para transformar a lista em um fluxo de daodos, permitindo aplicar operações de transformação
        // map(DadosListagemPaciente::new) -> método utilizado para converter cada objeto do tipo paciente em json DadosListagemPaciente, utilzando o constructor que criamos em DadosListagemPaciente
        // toList() -> método utilizado para coletar os resultados em uma nova lista do tipo DadosListagemPaciente, que é o formato que queremos retornar para a API
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPorPagina(Pageable paginacao) { //Page tem que importar o do domain (org.springframework.data.domain.Page). Pageable também.
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
        //return repository.findAll().stream().map(DadosListagemMedico::new).toList(); 
    }

    @PutMapping
    @Transactional // SPRING DATA JPA - Informa para o Springboot que o método abaixo é uma transação, ou seja, que as operações realizadas dentro do método devem ser tratadas como uma única unidade de trabalho, garantindo a integridade dos dados e a consistência do banco de dados.
    public void atualizar(@RequestBody DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        //var é uma palavra reservada do java para declarar uma variável sem especificar seu tipo: o tipo da variável é inferido pelo complidaor com base no valor que foi atribuido a ela.
        paciente.atualizarInformacoes(dados);
    }

    //AQUI ESTOU EXCLUINDO MESMO
    @DeleteMapping("/{id}")
   @Transactional // SPRING DATA JPA - Informa para o Springboot que o método abaixo é uma transação, ou seja, que as operações realizadas dentro do método devem ser tratadas como uma única unidade de trabalho, garantindo a integridade dos dados e a consistência do banco de dados.
    public void excluir(@PathVariable Integer id){//@PathVariable é uma anotação do Spring que indica que o valor do parâmetro id deve ser extraído da URL da requisição. 
    //Por exemplo, se a requisição for DELETE /medicos/5, o valor 5 será atribuído ao parâmetro id.
           repository.deleteById(id);
   }

    //Exclusão lógica - Uma regra de negocio que permite que um registro seja "excluido" sem ser apagado do banco de dados
    // @DeleteMapping("/{id}")
    // @Transactional // SPRING DATA JPA - Informa para o Springboot que o método abaixo é uma transação, ou seja, que as operações realizadas dentro do método devem ser tratadas como uma única unidade de trabalho, garantindo a integridade dos dados e a consistência do banco de dados.
    // public void alterarStatus(@PathVariable Integer id){
    //     var medico = repository.getReferenceById(id);
    //     medico.exclusaoLogica();
    // }

}
