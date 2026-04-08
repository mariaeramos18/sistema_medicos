package com.github.app.model.medico;

//DTO
public record DadosListagemMedico(
    Integer id,
    String nome,
    String email,
    String crm,
    Especialidade especialidade
) {
    //Método construtor recebendo o objeto Medico e convertendo para json DadosListagemMedico
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    } // O this é para chamar o constructor do record, passsando do medico para preencer os atributos da classe
    // O construtor acima é utilizado para converter um objeto tipo Medico em um json do tipo DadosListagemMedico, que é o formato que queremos devolver para nossa API
    // Também poderia ser feito no mesmo modelo do construtor de Medcio
    
}
