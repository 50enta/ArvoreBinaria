/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreBinaria;

import java.util.Objects;

/**
 *
 * @author 50enta
 * @param <T>
 */
public class No<T extends Comparable<T>> implements Cloneable {

    //atributos
    private T objecto; //depois arranjar maneiras de converter para Geral
    private No esquerda;
    private No direita;

    //construtores
    //outros métodos
    @Override
    public No clone() {
        try {
            return (No) super.clone(); //To change body of generated methods, choose Tools | Templates.
        } catch (CloneNotSupportedException ex) {
            return new No();
        }

    }

    /**
     * Método para adicionar valores à árvore. Éfeito o teste de maior, e tendo
     * em conta o resultado, é adicionado no seu lugar
     *
     * @param objecto
     */
    public void adicionar(T objecto) {
        if (this.isVazio()) {
            this.setObjecto(objecto);
        } else {
            if (objecto.compareTo(this.objecto) >= 0) {
                if (this.getDireita() == null) {
                    this.setDireita(new No());
                }
                this.getDireita().adicionar(objecto);
            } else {
                if (this.getEsquerda() == null) {
                    this.setEsquerda(new No());
                }
                this.getEsquerda().adicionar(objecto);
            }
        }
    }

    /**
     * Remove o nó que contém o objecto que se deseja remover, de acordo com o
     * número de filhos que o nó contém
     *
     * @param Objecto
     * @return
     */
    public boolean remover(T Objecto, No raiz) {
        if (contem(Objecto)) {
            switch (this.getFilhos(this.getNo(Objecto))) {
                case 0: //delectar nó sem filho
                    return removerCaso1(Objecto, raiz);
                case 1: //deletar nó com um filho
                    return removerCaso2(Objecto, raiz);
                case 2://deletar nó com dois filhos
                    return removerCaso3(Objecto, raiz);
            }
        }
        return false;
    }

    /**
     * Remoção de nó com 0 filhos, o caso básico
     *
     * @param Objecto
     * @return
     */
    private boolean removerCaso1(T Objecto, No raiz) {
        No ant = this.getNoAnterior(Objecto, new No());
        if (ant != null) {
            if (Objects.equals(ant.getDireita(), this.getNo(Objecto))) {
                ant.setDireita(null);
            } else {
                ant.setEsquerda(null);
            }
        } else {
            raiz = null;
        }
        //balanceiar
        return true;
    }

    /**
     * Remoção do nó com um filho, direita o esquerda
     *
     * @param Objecto
     * @return 847015731
     */
    private boolean removerCaso2(T Objecto, No raiz) {
        No ant1 = raiz.getNoAnterior(Objecto, null);
        if (ant1 != null) {
            if (Objects.equals(ant1.getDireita(), this.getNo(Objecto))) {
                ant1.setDireita((this.getNo(Objecto).getDireita() == null) ? this.getNo(Objecto).getEsquerda() : this.getNo(Objecto).getDireita());
            } else {
                ant1.setEsquerda((this.getNo(Objecto).getDireita() == null) ? this.getNo(Objecto).getEsquerda() : this.getNo(Objecto).getDireita());
            }

        } else {
            //remoção do valor contido pelo nó raiz
//            raiz = (raiz.getDireita() == null) ? raiz.getEsquerda() : raiz.getDireita();
            raiz = null;
        }
        //balanceiar

        return true;
    }

    /**
     * Remoção de nó com 2 filhos,
     *
     * @param Objecto
     * @return
     */
    private boolean removerCaso3(T Objecto, No raiz) {
        No noAnteriorAoASerRemovido = this.getNoAnterior(Objecto, null);
        if (noAnteriorAoASerRemovido != null) {
            No noASerRemovido = this.getNo(Objecto);
            No minimoDaquelaSubArvore = this.getMinimo(noASerRemovido.getDireita(), new No());
            noASerRemovido.remover(minimoDaquelaSubArvore.getObjecto(), raiz);
            minimoDaquelaSubArvore.setDireita(noASerRemovido.getDireita());
            minimoDaquelaSubArvore.setEsquerda(noASerRemovido.getEsquerda());

            if (Objects.equals(noAnteriorAoASerRemovido.getDireita(), noASerRemovido)) {
                noAnteriorAoASerRemovido.setDireita(minimoDaquelaSubArvore);
            } else {
                noAnteriorAoASerRemovido.setEsquerda(minimoDaquelaSubArvore);
            }
        } else {
            //
            // remoção caso o nó a ser removido for a raiz
            //
        }
        //balanceiar
        return true;
    }

    /**
     * Este método retorna nó que conté o maior valor dado uma subarvore (raiz)
     * passada por parâmetro
     *
     * @param no
     * @return
     */
    public No getMaximo(No no, No aux) { //aux vai guardo o valor anterior, enquanto percoremos a arvore
        if (no == null) {
            return aux;
        }
        aux = no;
        return getMaximo(no.getDireita(), aux);
    }

    /**
     * Este método retorna nó que conté o menor valor dado uma subarvore (raiz)
     * passada por parâmetro
     *
     * @param no
     * @return
     */
    public No getMinimo(No no, No aux) { //aux vai guardo o valor anterior, enquanto percoremos a arvore
        if (no == null) {
            return aux;
        }
        aux = no;
        return getMinimo(no.getEsquerda(), aux);
    }

    /**
     * metodo responsavel por calcular o numero de filhos que um no tem.
     *
     * @param no
     * @return
     */
    private int getFilhos(No no) {
        if (no != null) {
            if (no.getEsquerda() != null && no.getDireita() != null) {
                return 2;
            }
            if (no.getEsquerda() != null || no.getDireita() != null) {
                return 1;
            }
            return 0;
        }
        return -1;
    }

    /**
     * Este metodo sera responsavel por calcular a altura da subarvore dado o no
     * passado por parametro
     *
     * @param no
     * @param cont
     * @return
     */
    public int getAltura(No no, int cont) {
        int dir = 0, esq = 0;
        if (no != null) {
            if (no.getDireita() != null) {
                dir = this.getAltura(no.getDireita(), dir);
            }
            if (no.getEsquerda() != null) {
                esq = this.getAltura(no.getEsquerda(), esq);
            }

            cont++;
            return (dir >= esq) ? getAltura(no.getDireita(), cont) : getAltura(no.getEsquerda(), cont);
        }
        return cont;
    }

    /**
     * Retorna true se o objecto passado por parâmetro está contido no nó e
     * falso caso contrário
     *
     * @param objecto
     * @return true ou false
     */
    public boolean contem(T objecto) {
        if (!isVazio()) {
            if (Objects.equals(this.getObjecto(), objecto)) {
                return true;
            }

            if (objecto.compareTo(this.getObjecto()) >= 0) {
                if (this.getDireita() != null) {
                    return this.getDireita().contem(objecto);
                }

            } else {
                if (this.getEsquerda() != null) {
                    return this.getEsquerda().contem(objecto);
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Recebe um determinado valor e retorna o nó que o contém
     *
     * @param objecto
     * @return
     */
    public No getNo(T objecto) {
        try {
            if (contem(objecto)) {
                if (Objects.equals(this.getObjecto(), objecto)) {
                    return this;
                }
                if (objecto.compareTo(this.getObjecto()) >= 0) {
                    if (this.getDireita() != null) {
                        return this.getDireita().getNo(objecto);
                    }

                } else {
                    if (this.getEsquerda() != null) {
                        return this.getEsquerda().getNo(objecto);
                    }
                }
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao verificar se o objecto está contido na árvore");
        }
        return null;
    }

    /**
     * Recebe um objecto por parâmentro e retorna o nó que contém a referência
     * do nó que contém o objecto passado por parâmetro
     *
     * @param objecto
     * @return
     */
    public No getNoAnterior(T objecto, No noInicial) {
        try {
            if (contem(objecto)) {
                if (Objects.equals(this.getObjecto(), objecto)) {
                    if (noInicial == null) {
                        return null;
                    }
                    return noInicial;
                }
                if (objecto.compareTo(this.getObjecto()) >= 0) {
                    if (this.getDireita() != null) {
                        noInicial = this;
                        return this.getDireita().getNoAnterior(objecto, noInicial);
                    }

                } else {
                    if (this.getEsquerda() != null) {
                        noInicial = this;
                        return this.getEsquerda().getNoAnterior(objecto, noInicial);
                    }
                }
                System.out.println("Ta nulo");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao verificar se o objecto está contido na árvore");
        }
        System.out.println("Ta nuloo");
        return null;
    }

    /**
     * Verifica se o nó está vazio ou não
     *
     * @return
     */
    private boolean isVazio() {
        return this.getObjecto() == null;
    }

    /**
     * @return the objecto
     */
    public T getObjecto() {
        return objecto;
    }

    /**
     * @param objecto the objecto to set
     */
    public void setObjecto(T objecto) {
        this.objecto = objecto;
    }

    /**
     * @return the esquerda
     */
    public No getEsquerda() {
        return esquerda;
    }

    /**
     * @param esquerda the esquerda to set
     */
    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * @return the direita
     */
    public No getDireita() {
        return direita;
    }

    /**
     * @param direita the direita to set
     */
    public void setDireita(No direita) {
        this.direita = direita;
    }

}
