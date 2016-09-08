/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import entidades.Compra;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author NandaPC
 */
public class DaoDocumentos {
    
    public boolean salvarCompra(Compra compra) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase dataBase = client.getDatabase("bdnc-loja");
        MongoCollection<Document> collection = dataBase.getCollection("vendas");
        collection.insertOne(compra.toDocument());
        client.close();
        return true;
    }

    public List<Compra> buscarCompras() {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase dataBase = client.getDatabase("bdnc-loja");
        MongoCollection<Document> collection = dataBase.getCollection("vendas");
        List<Compra> compras = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Compra compra = new Compra();
            compras.add(compra.convertFromDocument(cursor.next()));
        }
        client.close();
        return compras;
    }
    
//    public boolean salvarCarrinho(Carrinho carrinhoDeCompras){
//        MongoClient client = new MongoClient("localhost", 27017);
//        MongoDatabase dataBase = client.getDatabase("bdnc-loja");
//        MongoCollection<Document> collection = dataBase.getCollection("carrinho");
//        collection.insertOne(carrinhoDeCompras.toDocument());
//        client.close();
//        return true;
//    }
//    
//    public Carrinho buscarCarrinho() {
//        MongoClient client = new MongoClient("localhost", 27017);
//        MongoDatabase dataBase = client.getDatabase("bdnc-loja");
//        MongoCollection<Document> collection = dataBase.getCollection("carrinho");
//        MongoCursor<Document> cursor = collection.find().iterator();
//        Carrinho carrinhoDeCompras = new Carrinho();
//        while (cursor.hasNext()) {
//            carrinhoDeCompras = carrinhoDeCompras.convertFromDocument(cursor.next());
//        }
//        client.close();
//        return carrinhoDeCompras;
//    }
}
