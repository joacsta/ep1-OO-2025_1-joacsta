package modos;

import java.io.Serializable;
import java.util.List;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nome;
    private int vagasDisponiveis;
    private List<String> preRequisitos;

    public Disciplina(String codigo, String nome, int vagasDisponiveis, List<String> preRequisitos) {
        this.codigo = codigo;
        this.nome = nome;
        this.vagasDisponiveis = vagasDisponiveis;
        this.preRequisitos = preRequisitos;
    }

    public void reduzirVaga() {
        if (vagasDisponiveis > 0) {
            vagasDisponiveis--;
        }
    }

    public void aumentarVaga() {
        vagasDisponiveis++;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public List<String> getPreRequisitos() {
        return preRequisitos;
    }

    @Override
    public String toString() {
        return nome + " (" + codigo + ") - Vagas: " + vagasDisponiveis;
    }
}