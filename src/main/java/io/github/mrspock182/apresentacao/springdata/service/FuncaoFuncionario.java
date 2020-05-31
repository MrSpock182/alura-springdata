package io.github.mrspock182.apresentacao.springdata.service;

import io.github.mrspock182.apresentacao.springdata.domain.Cargo;
import io.github.mrspock182.apresentacao.springdata.domain.Funcionario;
import io.github.mrspock182.apresentacao.springdata.repository.CargoRepository;
import io.github.mrspock182.apresentacao.springdata.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

@Service
public class FuncaoFuncionario {

    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository repository;

    public void inicio(Scanner scanner) {
        while (system) {
            System.out.println("1 - Cadastrar funcionario");
            System.out.println("2 - Alterar funcionario");
            System.out.println("3 - Visualizar funcionario");
            System.out.println("4 - Deletar funcionario");
            System.out.println("5 - Sair");
            Integer function = scanner.nextInt();

            switch (function) {
                case 1:
                    System.out.println("Cadastrar");
                    cadastrar(scanner);
                    break;
                case 2:
                    System.out.println("Alterar");
                    alterar(scanner);
                    break;
                case 3:
                    System.out.println("Visualizar");
                    visualizar(scanner);
                    break;
                case 4:
                    System.out.println("Deletar");
                    deletar(scanner);
                    break;
                default:
                    System.out.println("Finalizando");
                    system = false;
                    break;
            }
        }

    }

    private void cadastrar(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        repository.save(funcionario);
        System.out.println("Salvo");
    }

    private void alterar(Scanner scanner) {
        System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        repository.save(funcionario);
        System.out.println("Alterado");
    }

    private void visualizar(Scanner scanner) {
        System.out.println("Qual pagina");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by("nome"));

        Page<Funcionario> funcionarios = repository.findAll(pageable);
        System.out.println(funcionarios);
        System.out.println("Pagina Atual: " + funcionarios.getNumber());
        System.out.println("Total Paginas: " + (funcionarios.getTotalPages() - 1));
        funcionarios.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        repository.deleteById(id);
        System.out.println("Deletado");
    }
}
