/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import org.bson.Document;

/**
 *
 * @author NandaPC
 */
@Entity
@NamedQuery(name = "cliente.buscar", query = "SELECT c FROM Cliente c WHERE c.email = :email")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(long id, String nome, String email, String senha, String cpf, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Document toDocument() {
        Document document = new Document("id", id).append("nome", nome).append("email", email)
                .append("senha", senha).append("cpf", cpf).append("endereco", endereco.toDocument());
        return document;
    }

    public Cliente convertFromDocument(Document document) {
        this.id = document.getLong("id");
        this.nome = document.getString("nome");
        this.email = document.getString("email");
        this.senha = document.getString("senha");
        this.cpf = document.getString("cpf");
        this.endereco = document.get("endereco", Endereco.class);

        return this;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf + ", endereco=" + endereco + '}';
    }

}
