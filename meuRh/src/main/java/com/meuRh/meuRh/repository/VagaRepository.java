package com.meuRh.meuRh.repository;

import com.meuRh.meuRh.model.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VagaRepository extends CrudRepository<Vaga, String> {
    Vaga findByCodigo(int codigo);
    List<Vaga> findByNome(String nome);
}
