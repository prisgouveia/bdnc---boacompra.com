/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import org.bson.Document;

/**
 *
 * @author NandaPC
 */
@Entity
@NamedQuery(name = "produtos.todos", query = "SELECT p FROM Produto p")
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue
    private long id;
    private String descricao;
    private double valor;
    private byte [] foto;
    private double valorComQuantidade;
    private double qtdeVenda;

    public Produto() {
    }

    public Produto(long id, String descricao, double valor, byte[] foto) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public double getValorComQuantidade() {
        return valorComQuantidade;
    }

    public void setValorComQuantidade(double valorComQuantidade) {
        this.valorComQuantidade = valorComQuantidade;
    }

    public double getQtdeVenda() {
        return qtdeVenda;
    }

    public void setQtdeVenda(double qtdeVenda) {
        this.qtdeVenda = qtdeVenda;
    }
    
    public double calcularValorComQuantidade(){
        valorComQuantidade = valor * qtdeVenda;
        return valorComQuantidade;
    }
    
    public Document toDocument(){
        Document document = new Document("id", id).append("descricao", descricao)
                .append("valor", valor);
        return document;
    }
    
    public Produto convertFromDocument(Document document){
        this.id = document.getLong("id");
        this.descricao = document.getString("descricao");
        this.valor = document.getDouble("valor");
        
        return this;
    }

}
