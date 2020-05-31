package io.github.mrspock182.apresentacao.springdata.repository;

import io.github.mrspock182.apresentacao.springdata.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findBySalarioGreaterThan(Double salario);

    List<Funcionario> findByCargoFuncao(String funcao);

    @Query(value = "SELECT f.id, f.nome_funcionario, f.salario_funcionario FROM funcionarios f",
            nativeQuery = true)
    List<ProjectionFuncionario> all();

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataContratacao",
            nativeQuery = true)
    List<Funcionario> tempoCasa(LocalDate dataContratacao);

    @Query(value = "SELECT f FROM Funcionario f WHERE f.dataContratacao >= :dataContratacao AND" +
            " f.salario >= :salario")
    List<Funcionario> tempoCasaSalario(LocalDate dataContratacao, Double salario);
}
