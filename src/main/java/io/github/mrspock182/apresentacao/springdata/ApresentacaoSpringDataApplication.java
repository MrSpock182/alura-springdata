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
	private ControladorCargo controladorCargo;

	@Autowired
	private ControladorUnidade controladorUnidade;

	@Autowired
	private ControladorFuncionario controladorFuncionario;

	@Autowired
	private ControladorRelatorios controladorRelatorios;

	@Autowired
	private ControladorQueryDinamica controladorQueryDinamica;

	public static void main(String[] args) {
		SpringApplication.run(ApresentacaoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função deseja executar?");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionario");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Query Dinamicas");
			System.out.println("6 - Sair");

			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					controladorCargo.inicio(scanner);
					break;
				case 2:
					controladorUnidade.inicio(scanner);
					break;
				case 3:
					controladorFuncionario.inicio(scanner);
					break;
				case 4:
					controladorRelatorios.inicio(scanner);
					break;
				case 5:
					controladorQueryDinamica.inicio(scanner);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
	}
}
