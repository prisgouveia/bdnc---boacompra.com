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
public class Pagamento implements Serializable{
    
    private long id;
    private String empresa;
    private String titular;
    private int numero;
    private String codSeguranca;

    public Pagamento() {
        this.id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodSeguranca() {
        return codSeguranca;
    }

    public void setCodSeguranca(String codSeguranca) {
        this.codSeguranca = codSeguranca;
    }
    
    public Document toDocument(){
        Document document = new Document("id", id).append("empresa", empresa)
                .append("titular", titular).append("numero", numero).append("codSeguranca", codSeguranca);
        
        return document;
    }
    
    public Pagamento convertFromDocument(Document document){
        this.id = document.getLong("id");
        this.empresa = document.getString("empresa");
        this.titular = document.getString("titular");
        this.numero = document.getInteger("numero");
        this.codSeguranca = document.getString("codSeguranca");
        
        return this;
                
    }
}
