package io.github.mrspock182.apresentacao.springdata.service;

import io.github.mrspock182.apresentacao.springdata.domian.Funcionario;
import io.github.mrspock182.apresentacao.springdata.repository.FuncionarioRepository;
import io.github.mrspock182.apresentacao.springdata.specification.SpecificationFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class FuncaoQueryDinamica {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private FuncionarioRepository repository;

    public void inicio(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        if(nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        if(cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        if(salario == 0) {
            salario = null;
        }

        System.out.println("Digite a data de contracao");
        String data = scanner.next();

        LocalDate localDate;
        if(data.equalsIgnoreCase("NULL")) {
            localDate = null;
        } else {
            localDate = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = repository.findAll(
                Specification.where(SpecificationFuncionario.nome(nome))
                        .or(SpecificationFuncionario.cpf(cpf))
                        .and(SpecificationFuncionario.salario(salario))
                        .or(SpecificationFuncionario.dataContratacao(localDate)));

        funcionarios.forEach(System.out::println);
    }

}
