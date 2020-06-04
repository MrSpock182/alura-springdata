package io.github.mrspock182.apresentacao.springdata.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "unidade")
public class UnidadeTrabalho implements Serializable {
    @Id
    @Column(name = "idUnidade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String endereco;
    @ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.LAZY)
    private List<Funcionario> funcionarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "Unidades: " + "id:" + id +
                "| descricao:" + descricao +
                "| endereco:" + endereco;
    }
}
