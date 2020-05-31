package io.github.mrspock182.apresentacao.springdata.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nomeFuncionario")
    private String nome;
    @Column(name = "cpfFuncionario")
    private String cpf;
    @Column(name = "salarioFuncionario")
    private Double salario;
    private LocalDate dataContratacao;
    @ManyToOne
    @JoinColumn(name="cargo_id", nullable=false)
    private Cargo cargo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario: " + "id:" + id
                + "| nome:'" + nome +
                "| cpf:" + cpf +
                "| salario:" + salario +
                "| dataContratacao:" + dataContratacao +
                "| cargo:" + cargo.getFuncao();
    }
}
