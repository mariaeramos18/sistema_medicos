package com.github.app.model.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Integer>{  //preciso passar a regra de negócio: a classe(Medico) e o tipo de id (Integer)
    
    

}
