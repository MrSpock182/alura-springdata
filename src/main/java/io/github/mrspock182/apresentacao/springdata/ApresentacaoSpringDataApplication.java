package io.github.mrspock182.apresentacao.springdata;

import io.github.mrspock182.apresentacao.springdata.domian.Cargo;
import io.github.mrspock182.apresentacao.springdata.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ApresentacaoSpringDataApplication implements CommandLineRunner {

	@Autowired
	private CargoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ApresentacaoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo = new Cargo();
		cargo.setFuncao("DESENVOLVEDOR");

		repository.save(cargo);
	}
}
