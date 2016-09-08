/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import com.mongodb.BasicDBList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.bson.Document;

/**
 *
 * @author NandaPC
 */
@Entity
public class Carrinho implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    @Transient
    private int qtdeItens;
    private double valor;
    private List<Produto> produtos;

    public Carrinho() {
        this.produtos = new ArrayList<>();
        this.qtdeItens = 0;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQtdeItens() {
        return qtdeItens;
    }

    public void setQtdeItens(int qtdeItens) {
        this.qtdeItens = qtdeItens;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public Document toDocument(){
        Document document = new Document("id", id).append("qtdeItens", qtdeItens)
                .append("valor", valor).append("produtos", produtos);
        BasicDBList produtoList = new BasicDBList();
        for (Produto produto : produtos) {
            produtoList.add(produto.toDocument());
        }
        document.append("produtos", produtoList);
        
        return document;
    }
    
    public Carrinho convertFromDocument(Document document){
        this.id = document.getLong("id");
        this.qtdeItens = document.getInteger("qtdeItens");
        this.valor = document.getDouble("valor");
        this.produtos = document.get("empresa",List.class);
        
        return this;
    }
    
     public boolean addProduto(Produto produto) {
        if (!produtos.contains(produto)) {
            this.produtos.add(produto);
            this.valor = calculaValorParcial();
            this.qtdeItens ++;
            return true;
        }
        return false;
    }

    private double calculaValorParcial() {
        long aux = 0;
        for (Produto produto : produtos) {
            aux += produto.calcularValorComQuantidade();
        }
        return aux;
    }
}
