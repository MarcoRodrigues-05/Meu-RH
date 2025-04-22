package com.meuRh.meuRh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Candidato {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String rg;
    @NotEmpty
    private String nomeCandidato;
    @NotEmpty
    private String email;
    @ManyToOne
    private Vaga vaga;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public @NotEmpty String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(@NotEmpty String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
