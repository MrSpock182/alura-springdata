package io.github.mrspock182.apresentacao.springdata.repository;

import io.github.mrspock182.apresentacao.springdata.domian.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    List<Funcionario> findBySalarioGreaterThan(Double salario);

    List<Funcionario> findByCargoFuncao(String funcao);

    @Query(value = "SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario = :salario AND f.dataContratacao >= :dataContratacao")
    List<Funcionario> pesquisaNomeSalarioContrato(String nome, Double salario, LocalDate dataContratacao);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataContratacao",
            nativeQuery = true)
    List<Funcionario> pesquisaTempoEmpresa(LocalDate dataContratacao);
}