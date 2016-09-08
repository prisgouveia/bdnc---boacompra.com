/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Compra;
import entidades.Produto;
import entidades.RelTipes;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 *
 * @author NandaPC
 */
public class DaoGrafo {
    
     public void salvarRelacionamentos(Compra compra) {

        String path =  "C:\\Users\\Pris\\Documents\\Neo4j";
        File file = new File(path);
        GraphDatabaseService graph = new GraphDatabaseFactory().newEmbeddedDatabase(file);

        try (Transaction tx = graph.beginTx()) {
            Node noCliente = graph.findNode(DynamicLabel.label("Cliente"), "idCliente", compra.getCliente().getId());
            if (noCliente == null) {
                noCliente = graph.createNode(DynamicLabel.label("Cliente"));
                noCliente.setProperty("idCliente", compra.getCliente().getId());
            }

            List<Produto> produtos = compra.getCarrinho().getProdutos();
            for (Produto produto : produtos) {
                Node noProduto = graph.findNode(DynamicLabel.label("Produto"), "idProduto", produto.getId());
                if (noProduto == null) {
                    noProduto = graph.createNode(DynamicLabel.label("Produto"));
                    noProduto.setProperty("idProduto", produto.getId());
                }
                Relationship relCliPro = noCliente.createRelationshipTo(noProduto, RelTipes.COMPROU);
            }
            tx.success();
        }
        graph.shutdown();
    }

    public Map<Long, Integer> buscarRelacionamentos(Produto produto) {

        String path =  "C:\\Users\\Pris\\Documents\\Neo4j";
        File file = new File(path);
        GraphDatabaseService graph = new GraphDatabaseFactory().newEmbeddedDatabase(file);
        Map<Long, Integer> produtos = null;

        try (Transaction tx = graph.beginTx()) {
            Node noProduto = graph.findNode(DynamicLabel.label("Produto"), "idProduto", produto.getId());
            if (noProduto != null) {
                Iterator<Relationship> relacionamentosProdutoCliente = noProduto.getRelationships(RelTipes.COMPROU).iterator();
                produtos = new HashMap<>();
                while (relacionamentosProdutoCliente.hasNext()) {
                    Node nodeCliente = relacionamentosProdutoCliente.next().getStartNode();
                    Iterable<Relationship> relacionamentosClienteProdutos = nodeCliente.getRelationships();
                    for (Relationship r : relacionamentosClienteProdutos) {
                        Node nodeProduto = r.getEndNode();
                        long id = (Long) nodeProduto.getProperty("idProduto");
                        if (!produtos.containsKey(id)) {
                            produtos.put(id, 1);
                        } else {
                            produtos.put(id, produtos.get(id) + 1);
                        }
                    }
                }
            } 
            tx.success();
        }
        graph.shutdown();
        return produtos;
    }
}
