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
import org.bson.Document;

/**
 *
 * @author NandaPC
 */
@Entity
public class Endereco implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private String numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {
    }

    public Endereco(long id, String numero, String rua, String bairro, String cidade, String estado) {
        this.id = id;
        this.numero = numero;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Document toDocument(){
        Document document = new Document("id", id).append("numero", numero).append("rua", rua)
                .append("bairro", bairro).append("cidade", cidade).append("estado", estado);
        return document;
    }
    
    public Endereco convertFromDocument(Document document){
        this.id = document.getLong("id");
        this.numero = document.getString("numero");
        this.rua = document.getString("rua");
        this.bairro = document.getString("bairro");
        this.estado = document.getString("estado");
        this.cidade = document.getString("cidade"); 
        
        return this;
    }
}
