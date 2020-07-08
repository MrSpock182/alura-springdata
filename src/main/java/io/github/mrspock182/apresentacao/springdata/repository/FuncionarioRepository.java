package io.github.mrspock182.apresentacao.springdata.repository;

import io.github.mrspock182.apresentacao.springdata.domian.Funcionario;
import io.github.mrspock182.apresentacao.springdata.projection.ProjectionFuncionario;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {
    List<Funcionario> findBySalarioGreaterThan(Double salario);

    List<Funcionario> findByCargoFuncao(String funcao);

    @Query(value = "SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario = :salario AND f.dataContratacao >= :dataContratacao")
    List<Funcionario> pesquisaNomeSalarioContrato(String nome, Double salario, LocalDate dataContratacao);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataContratacao",
            nativeQuery = true)
    List<Funcionario> pesquisaTempoEmpresa(LocalDate dataContratacao);

    @Query(value = "SELECT f FROM Funcionario f WHERE f.dataContratacao >= :dataContratacao AND" +
            " f.salario >= :salario")
    List<Funcionario> tempoCasaSalario(LocalDate dataContratacao, Double salario);

    @Query(value = "SELECT f.id_funcionario, f.nome_funcionario, f.salario_funcionario" +
            " FROM funcionarios f ORDER BY f.salario_funcionario DESC",
            nativeQuery = true)
    List<ProjectionFuncionario> pesquisaFuncionarioSalario();
}