package com.meuRh.meuRh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String descricao;
    @NotEmpty
    private String data;
    @NotEmpty
    private String salario;
    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Candidato> candidados;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public @NotEmpty String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty String nome) {
        this.nome = nome;
    }

    public @NotEmpty String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotEmpty String descricao) {
        this.descricao = descricao;
    }

    public @NotEmpty String getData() {
        return data;
    }

    public void setData(@NotEmpty String data) {
        this.data = data;
    }

    public @NotEmpty String getSalario() {
        return salario;
    }

    public void setSalario(@NotEmpty String salario) {
        this.salario = salario;
    }

    public List<Candidato> getCandidados() {
        return candidados;
    }

    public void setCandidados(List<Candidato> candidados) {
        this.candidados = candidados;
    }
}
