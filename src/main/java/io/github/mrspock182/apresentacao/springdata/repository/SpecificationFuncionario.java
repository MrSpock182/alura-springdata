package io.github.mrspock182.apresentacao.springdata.repository;

import io.github.mrspock182.apresentacao.springdata.domain.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;
import java.util.function.Predicate;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;
import static javafx.scene.input.KeyCode.T;

public class SpecificationFuncionario {

//    public static Specification<Funcionario> nome(String nome) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.like(root.get("nome"), nome);
//    }
//
//    public static Specification<Funcionario> cpf(String cpf) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get("cpf"), cpf);
//    }
//
//    public static Specification<Funcionario> salario(Double salario) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.greaterThan(root.get("salario"), salario);
//    }
//
//    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
//    }


    public static Specification<Funcionario> cargo(String cargo) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("Cargo_.funcao"), cargo);
    }

}
