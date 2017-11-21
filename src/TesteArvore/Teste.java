/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteArvore;

import arvoreBinaria.Arvore;

/**
 *
 * @author 50enta
 */
public class Teste {

    public static void main(String[] args) {

        Arvore<Estudante> arv = new Arvore<>();
        arv.adicionar(new Estudante("Válter ", "Informática", 13));
        arv.adicionar(new Estudante("Micaela", "Informática", 17));
        arv.adicionar(new Estudante("Samira", "Informática", 67));
        arv.adicionar(new Estudante("Cinquenta", "Informática", 143));
        arv.adicionar(new Estudante("Silja", "Informática", 3));
        arv.adicionar(new Estudante("Bezerra", "Informática", 12));

        System.out.println("................");
        for (Object l : arv) {
            System.out.print(l.toString() + ", ");
        }
        System.out.println("................");
        System.out.println(arv.getAltura());
        System.out.println(arv.remover(new Estudante("Bezerra", "Informática", 13)));
//        No a = arv.getNoAnterior(10);
        System.out.println(arv.contem(new Estudante("Bezerra", "Informática", 13))); //verificar se o elemento está contido
//        System.out.println(a.getEsquerda().getObjecto());
//        System.out.println(a.getDireita().getObjecto()); //em caso de retorno ser nulo
        System.out.println("................");
        for (Object l : arv) {
            System.out.print(l.toString() + ", ");
        }
        System.out.println("................");
    }
}
