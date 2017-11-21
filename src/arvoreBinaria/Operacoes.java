/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreBinaria;

/**
 *
 * @author 50enta
 */
public interface Operacoes<T extends Comparable<T>> {
    public void adicionar(T objecto);
    public boolean isVazio();
    public boolean contem(T objecto);
    public boolean remover(T objecto);
    public No getNo(T Objecto);
    public No getNoAnterior(T objecto);
    public No getMaximo();
    public No getMinimo();
}
