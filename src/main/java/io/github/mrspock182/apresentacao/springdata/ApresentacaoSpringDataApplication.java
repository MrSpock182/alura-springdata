package io.github.mrspock182.apresentacao.springdata;

import io.github.mrspock182.apresentacao.springdata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@EnableJpaRepositories
@SpringBootApplication
public class ApresentacaoSpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	@Autowired
	private FuncaoCargo funcaoCargo;

	@Autowired
	private FuncaoUnidade funcaoUnidade;

	@Autowired
	private FuncaoFuncionario funcaoFuncionario;

	@Autowired
	private FuncaoRelatorios funcaoRelatorios;

	@Autowired
	private FuncaoQueryDinamica funcaoQueryDinamica;

	public static void main(String[] args) {
		SpringApplication.run(ApresentacaoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função deseja executar?");
			System.out.println("1 - Funcionario");
			System.out.println("2 - Cargo");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Query Dinamicas");
			System.out.println("6 - Sair");

			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					funcaoFuncionario.inicio(scanner);
					break;
				case 2:
					funcaoCargo.inicio(scanner);
					break;
				case 3:
					funcaoUnidade.inicio(scanner);
					break;
				case 4:
					funcaoRelatorios.inicio(scanner);
					break;
				case 5:
					funcaoQueryDinamica.inicio(scanner);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
	}
}
