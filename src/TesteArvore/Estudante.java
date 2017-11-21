/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteArvore;

/**
 *
 * @author 50enta
 */
public class Estudante implements Comparable<Estudante> {

    private String nome;
    private String curso;
    private int idade;

    
    public Estudante(String nome, String curso, int idade){
        this.setNome(nome);
        this.setCurso(curso);
        this.setIdade(idade);
    }

    @Override
    public String toString() {
        return this.getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public int compareTo(Estudante t) {
        return this.getNome().compareToIgnoreCase(((this).getNome()));
    }

}
