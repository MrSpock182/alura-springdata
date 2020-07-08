package io.github.mrspock182.apresentacao.springdata.repository;

import io.github.mrspock182.apresentacao.springdata.domian.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {
    Cargo findByFuncao(String nome);
}
