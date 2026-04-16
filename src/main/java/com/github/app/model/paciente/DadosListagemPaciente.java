package com.github.app.model.paciente;

public record DadosListagemPaciente(
    Integer id,
    String nome,
    String cpf,
    String email,
    String telefone
) {
        //Método construtor recebendo o objeto Paciente e convertendo para json DadosListagemPaciente
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone());
    }
}
