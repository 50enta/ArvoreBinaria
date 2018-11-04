package arvoreBinaria;

import java.util.Iterator;

/**
 * Classe que refenrencia a base da árvore binária, É a interface entre o usuáio
 * e os métodos que se deseja acessar, ou essenciais para o funcionamento da
 * árvore
 *
 * @author 50enta
 * @param <T>
 */
public class Arvore<T extends Comparable<T>> implements Operacoes<T>, Iterable<T>, Cloneable {

    //atriutos
    private No raiz;

    /**
     * Adiciona os valores na árvores
     *
     * @param objecto
     */
    @Override
    public void adicionar(T objecto) {
        if (this.isVazio()) {
            this.setRaiz(new No());
        }
        this.getRaiz().adicionar(objecto);
    }

    /**
     * Retorna a altura da árvore, tendo em conta que a altura é dada pela
     * subárovore mais profunda Atenção que caso ocorra algum problema, o valor
     * de retorno será o tradicional -1.
     *
     * @return
     */
    public int getAltura() {
        if (!isVazio()) {
            return this.getRaiz().getAltura(this.getRaiz(), 0) - 1; //acontagem é baseiada em número de raizes, por isso desconta -se -1
        }
        return -1;
    }

    /**
     * Reebe por praâmetro um objecto e verifica se ele está contido na arvore
     * retornando true em caso e afirmativo e falso o contrário
     *
     * @param objecto
     * @return
     * @throws Exception
     */
    @Override
    public boolean contem(T objecto) {
        if (!isVazio()) {
            return this.getRaiz().contem(objecto);
        }
        return false;
    }

    /**
     * Remove um determinado objecto, passado por parâmetro
     *
     * @param objecto
     * @return
     */
    public boolean remover(T objecto) {
        if (!isVazio()) {
            return getRaiz().remover(objecto, raiz);
        }
        return false;
    }

    /**
     * Retorna o nó que contém o maior valor da árvore
     *
     * @return
     */
    @Override
    public No getMaximo() {
        if (!isVazio()) {
            return this.getRaiz().getMaximo(this.getRaiz(), new No());
        }
        return null;
    }

    /**
     * Retorna o nó que contém o menor valor da árvore
     *
     * @return
     */
    @Override
    public No getMinimo() {
        if (!isVazio()) {
            return this.getRaiz().getMinimo(this.getRaiz(), new No());
        }
        return this.getRaiz();
    }

    /**
     * Recebe por parâmetro um objecto e retorna o nó que o contém
     *
     * @param objecto
     * @return
     */
    @Override
    public No getNo(T objecto) {
        if (!isVazio()) {
            return this.getRaiz().getNo(objecto);
        }
        return null;
    }

    /**
     * Recebe por parâmetro um objecto e retorna o nó anterior ao nó que o
     * contém o objecto a ser pesquisado
     *
     * @param objecto
     * @return
     */
    @Override
    public No getNoAnterior(T objecto) {
        if (!isVazio()) {
            return this.getRaiz().getNoAnterior(objecto, null);
        }
        return null;
    }

    /**
     * Verifica se a árvore está vazia, retornando true em caso afirmatico e
     * false caso negativo
     *
     * @return
     */
    @Override
    public boolean isVazio() {
        return this.getRaiz() == null;
    }

    /**
     * Retorna a raiz da árvore
     *
     * @return the raiz
     */
    public No getRaiz() {
        return raiz;
    }

    /**
     *
     * @param raiz the raiz to set
     */
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            No contr = (No) raiz.clone();
            T centro = (T) contr.getObjecto();
            boolean ja = false;

            @Override
            public boolean hasNext() {
                return (contr != null) && contr.getAltura(contr, 0) - 1 > 0;
            }

            @Override
            public T next() {

                No a = contr.getMinimo(contr.getEsquerda(), contr);
                
                if (a.getObjecto().compareTo(centro) == -1 && !ja) {
                    contr.remover(a.getObjecto(), contr);
                    return (T) a.getObjecto();
                }
                else {
                    if (a.getObjecto().compareTo(centro) >= 0) {
                        if (!ja) {
                            ja = true;
                            return (T)centro;
                        }
                        a = contr.getMinimo(contr.getDireita(), contr);
                        contr.remover(a.getObjecto(), contr);
                        return (T)a.getObjecto();
                    }
                }
                return null;
            }
        };
    }

}
 