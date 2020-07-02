package io.github.mrspock182.apresentacao.springdata.repository;

import io.github.mrspock182.apresentacao.springdata.domian.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
}