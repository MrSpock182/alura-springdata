package io.github.mrspock182.apresentacao.springdata.service;

import io.github.mrspock182.apresentacao.springdata.domian.Cargo;
import io.github.mrspock182.apresentacao.springdata.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ControladorCargo {

    private Boolean system = true;

    @Autowired
    private CargoRepository repository;

    public void inicio(Scanner scanner) {
        while (system) {
            System.out.println("1 - Cadastrar cargo");
            System.out.println("2 - Alterar cargo");
            System.out.println("3 - Visualizar cargos");
            System.out.println("4 - Deletar ccargo");
            System.out.println("5 - Sair");
            Integer function = scanner.nextInt();

            switch (function) {
                case 1:
                    System.out.println("Cadastrar");
                    inserir(scanner);
                    break;
                case 2:
                    System.out.println("Alterar");
                    alterar(scanner);
                    break;
                case 3:
                    System.out.println("Visualizar");
                    visualizar();
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

    private void inserir(Scanner scanner) {
        System.out.println("Nome do Cargo");
        String nomeCargo = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setFuncao(nomeCargo);

        repository.save(cargo);

        System.out.println("Salvo");
    }

    private void alterar(Scanner scanner) {
        System.out.println("Id do Cargo");
        Integer id = scanner.nextInt();

        System.out.println("Nome do Cargo");
        String nomeCargo = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setFuncao(nomeCargo);

        repository.save(cargo);
        System.out.println("Alterado");
    }

    private void visualizar() {
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id do Cargo");
        Integer id = scanner.nextInt();

        repository.deleteById(id);

        System.out.println("Deletar");
    }

}