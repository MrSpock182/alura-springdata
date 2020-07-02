package io.github.mrspock182.apresentacao.springdata.repository;

import io.github.mrspock182.apresentacao.springdata.domian.UnidadeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends CrudRepository<UnidadeTrabalho, Integer> {
}