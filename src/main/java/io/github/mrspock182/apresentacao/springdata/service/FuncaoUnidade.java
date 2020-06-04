package io.github.mrspock182.apresentacao.springdata.service;

import io.github.mrspock182.apresentacao.springdata.domain.Cargo;
import io.github.mrspock182.apresentacao.springdata.domain.Funcionario;
import io.github.mrspock182.apresentacao.springdata.domain.UnidadeTrabalho;
import io.github.mrspock182.apresentacao.springdata.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class FuncaoUnidade {

    private Boolean system = true;

    @Autowired
    private UnidadeTrabalhoRepository repository;

    public void inicio(Scanner scanner) {
        while (system) {
            System.out.println("1 - Cadastrar unidade");
            System.out.println("2 - Alterar unidade");
            System.out.println("3 - Visualizar unidades");
            System.out.println("4 - Deletar unidade");
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
        System.out.println("Digite o nome da unidade");
        String nome = scanner.next();

        System.out.println("Digite o endereco");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        repository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void alterar(Scanner scanner) {
        System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome da unidade");
        String nome = scanner.next();

        System.out.println("Digite o endereco");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        repository.save(unidadeTrabalho);
        System.out.println("Alterado");
    }

    private void visualizar(Scanner scanner) {
        List<UnidadeTrabalho> list = repository.findAll();
        list.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        repository.deleteById(id);
        System.out.println("Deletado");
    }

}
