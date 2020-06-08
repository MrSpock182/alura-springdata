package io.github.mrspock182.apresentacao.springdata.service;

import io.github.mrspock182.apresentacao.springdata.domain.Funcionario;
import io.github.mrspock182.apresentacao.springdata.domain.UnidadeTrabalho;
import io.github.mrspock182.apresentacao.springdata.repository.FuncionarioRepository;
import io.github.mrspock182.apresentacao.springdata.repository.ProjectionFuncionario;
import io.github.mrspock182.apresentacao.springdata.repository.SpecificationFuncionario;
import io.github.mrspock182.apresentacao.springdata.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class FuncaoRelatorios {

    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private UnidadeTrabalhoRepository unidadeRepository;

    public void inicio(Scanner scanner) {
        while (system) {
            System.out.println("Qual função deseja executar?");
            System.out.println("1 - Relatorio de Salarios");
            System.out.println("2 - Pequisa de Salarios");
            System.out.println("3 - Pesquisa de Tempo na Empresa");
            System.out.println("4 - Pesquisa de Tempo na Empresa com Salario");
            System.out.println("5 - Pesquisa Dinamica");
            System.out.println("6 - Pesquisa Funcionario por cargo");
            System.out.println("7 - Funcionarios por unidade");
            System.out.println("8 - Sair");

            Integer function = scanner.nextInt();

            switch (function) {
                case 1:
                    System.out.println("Relatorio de Salarios");
                    relatorioSalario();
                    break;
                case 2:
                    System.out.println("Pequisa de Salarios");
                    pesquisaSalario(scanner);
                    break;
                case 3:
                    System.out.println("Pesquisa de Tempo na Empresa");
                    pesquisaTempoEmpresa(scanner);
                    break;
                case 4:
                    System.out.println("Pesquisa de Tempo na Empresa com Salario");
                    pesquisaTempoEmpresaComSalario(scanner);
                    break;
                case 5:
                    System.out.println("Pesquisa Dinamica");
                    pesquisaDinamica(scanner);
                    break;
                case 6:
                    System.out.println("Pesquisa Dinamica");
                    cargoFuncao(scanner);
                    break;
                case 7:
                    System.out.println("Funcionarios por Unidade");
                    funcionariosPorUnidade(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void funcionariosPorUnidade(Scanner scanner) {
        System.out.println("Informe a unidade");
        Integer id = scanner.nextInt();

        Optional<UnidadeTrabalho> optional = unidadeRepository.findById(id);
        UnidadeTrabalho unidadeTrabalho = optional.get();
        System.out.println("Unidade: " + unidadeTrabalho.getDescricao());
        unidadeTrabalho.getFuncionarios().forEach(f -> System.out.println("Funcionario: " + f.getNome()));
    }

    private void relatorioSalario() {
        List<ProjectionFuncionario> list = repository.all();
        list.forEach(f -> System.out.println("Funcionario: id:" + f.getId() + " | nome:"
                + f.getNome_funcionario() + " | salario:" + f.getSalario_funcionario()));
    }

    private void pesquisaSalario(Scanner scanner) {
        System.out.println("Salario a pesquisar");
        Double salario = scanner.nextDouble();
        List<Funcionario> funcionarios = repository.findBySalarioGreaterThan(salario);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaTempoEmpresa(Scanner scanner) {
        System.out.println("Data contratação");
        String data = scanner.next();

        List<Funcionario> funcionarios = repository.tempoCasa(LocalDate.parse(data, formatter));
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaTempoEmpresaComSalario(Scanner scanner) {
        System.out.println("Data contratação");
        String data = scanner.next();

        System.out.println("Salario");
        double salario = scanner.nextDouble();

        List<Funcionario> funcionarios = repository.tempoCasaSalario(LocalDate.parse(data, formatter), salario);
        funcionarios.forEach(System.out::println);
    }

    private void cargoFuncao(Scanner scanner) {
        System.out.println("Digite o cargo");
        String cargo = scanner.next();

        List<Funcionario> funcionarios = repository.findByCargoFuncao(cargo);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaDinamica(Scanner scanner) {
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
                        .or(SpecificationFuncionario.salario(salario))
                        .or(SpecificationFuncionario.dataContratacao(localDate)));

        funcionarios.forEach(System.out::println);
    }

}
