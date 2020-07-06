package io.github.mrspock182.apresentacao.springdata.service;

import io.github.mrspock182.apresentacao.springdata.domian.Cargo;
import io.github.mrspock182.apresentacao.springdata.domian.Funcionario;
import io.github.mrspock182.apresentacao.springdata.domian.UnidadeTrabalho;
import io.github.mrspock182.apresentacao.springdata.repository.CargoRepository;
import io.github.mrspock182.apresentacao.springdata.repository.FuncionarioRepository;
import io.github.mrspock182.apresentacao.springdata.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ControladorRelatorios {

    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository repository;

    public void inicio(Scanner scanner) {
        while (system) {
            System.out.println("Qual função deseja executar?");
            System.out.println("1 - Pesquisa de Cargo:");
            System.out.println("2 - Pesquisa Salario Maior Que:");
            System.out.println("3 - Pesquisa Funcionário por cargo:");
            System.out.println("4 - Pesquisa Funcionário por nome, salário e contratação:");
            System.out.println("5 - Pesquisa Funcionário por tempo de empresa:");
            System.out.println("6 - Sair");

            Integer function = scanner.nextInt();

            switch (function) {
                case 1:
                    System.out.println("Pesquisa de Cargo");
                    pesquisaCargo(scanner);
                    break;
                case 2:
                    System.out.println("Pesquisa de Salarios");
                    pesquisaSalarioMaiorQue(scanner);
                    break;
                case 3:
                    System.out.println("Pesquisa de Funcionario por cargo");
                    pesquisaFuncionarioPorCargo(scanner);
                    break;
                case 4:
                    System.out.println("Pesquisa Nome, Calário e Contrato");
                    pesquisaNomeSalarioContrato(scanner);
                    break;
                case 5:
                    System.out.println("Pesquisa Funcionário por tempo de empresa");
                    pesquisaTempoEmpresa(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void pesquisaCargo(Scanner scanner) {
        System.out.println("Informe o nome do cargo");
        String nameCargo = scanner.next();

        Cargo cargo = cargoRepository.findByFuncao(nameCargo);
        cargo.toString();
    }

    private void pesquisaSalarioMaiorQue(Scanner scanner) {
        System.out.println("Salario a pesquisar");
        Double salario = scanner.nextDouble();
        List<Funcionario> funcionarios = repository.findBySalarioGreaterThan(salario);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaFuncionarioPorCargo(Scanner scanner) {
        System.out.println("Digite o cargo");
        String cargo = scanner.next();

        List<Funcionario> funcionarios = repository.findByCargoFuncao(cargo);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaNomeSalarioContrato(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contratação");
        String data = scanner.next();

        List<Funcionario> funcionarios = repository.pesquisaNomeSalarioContrato(nome, salario,
                LocalDate.parse(data, formatter));
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaTempoEmpresa(Scanner scanner) {
        System.out.println("Data contratação");
        String data = scanner.next();

        List<Funcionario> funcionarios = repository.pesquisaTempoEmpresa(LocalDate.parse(data, formatter));
        funcionarios.forEach(System.out::println);
    }

}