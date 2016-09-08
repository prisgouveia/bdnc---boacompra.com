/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import org.bson.Document;

/**
 *
 * @author NandaPC
 */
public class Compra implements Serializable{

    private long id;
    private double valor;
    private Cliente cliente;
    private Carrinho carrinho;
    private Endereco enderecoParaEntrega;
    private Pagamento pagamento;

    public Compra() {
        this.id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Endereco getEnderecoParaEntrega() {
        return enderecoParaEntrega;
    }

    public void setEnderecoParaEntrega(Endereco enderecoParaEntrega) {
        this.enderecoParaEntrega = enderecoParaEntrega;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    public Document toDocument(){
        Document document = new Document("id", id).append("valor", valor);
        document.append("cliente", cliente.toDocument());
        document.append("carrinho", carrinho.toDocument());
                
    return document;
    }
    
    public Compra convertFromDocument(Document document){
        this.id = document.getLong("id");
        this.valor = document.getDouble("valor");
        this.cliente = document.get("cliente", Cliente.class);
        this.carrinho = document.get("carrinho", Carrinho.class);
        this.pagamento = document.get("pagamento", Pagamento.class);
        this.enderecoParaEntrega = document.get("enderecoParaEntrega", Endereco.class);
        
        return this;
    }
}
