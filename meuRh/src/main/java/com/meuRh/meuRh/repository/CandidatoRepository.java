package com.meuRh.meuRh.repository;

import com.meuRh.meuRh.model.Candidato;
import com.meuRh.meuRh.model.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidatoRepository extends CrudRepository<Candidato, String> {
    Iterable<Candidato> findByVaga(Vaga vaga);
    Candidato findByRg(String rg);
    Candidato findById(int id);
    List<Candidato> findByNomeCandidato(String nomeCandidato);
}
