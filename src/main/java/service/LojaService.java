/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import dao.Dao;
import dao.DaoDocumentos;
import dao.DaoGrafo;
import dao.DaoJpa;
import entidades.Carrinho;
import entidades.Compra;
import entidades.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 *
 * @author NandaPC
 */
public class LojaService {

    private Dao<Produto> daoJpa = new DaoJpa<>();
    private DaoDocumentos daoMongo = new DaoDocumentos();
    private DaoGrafo daoGrafo = new DaoGrafo();

    public boolean salvarProduto(Produto produto) {
        return daoJpa.salvar(produto);
    }

    public List<Produto> listarProdutos() {
        return daoJpa.consultaLista("produtos.todos", null);
    }

    public Produto buscarProduto(long id) {
        return daoJpa.buscar(id, Produto.class);
    }

    public boolean salvarCarrinhoDeCompras(Carrinho carrinhoDeCompras) {
        Gson gson = new Gson();
        String aux = gson.toJson(carrinhoDeCompras);
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.setex("carrinho", 43200, aux);
        jedis.close();
        
//        daoMongo.salvarCarrinho(carrinhoDeCompras);
        return true;
    }

    public Carrinho buscarCarrinhoDeCompras() {
        Gson gson = new Gson();
        Jedis jedis = new Jedis("localhost", 6379);
        String aux = jedis.get("carrinho");
        Carrinho carrinho = gson.fromJson(aux, Carrinho.class);
        jedis.close();
        return carrinho;
//        return daoMongo.buscarCarrinho();
    }

    public Carrinho removerProdutoCarrinho(Carrinho carrinhoDeCompras, Produto produto) {
        List<Produto> produtos = carrinhoDeCompras.getProdutos();
        for (Produto p : produtos) {
            if (p.getId() == produto.getId()) {
                produtos.remove(p);
            }
        }
        carrinhoDeCompras.setProdutos(produtos);
        return carrinhoDeCompras;
    }

    public void limparCarrinho(Carrinho carrinhoDeCompras) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.expire("carrinho", 0);
        jedis.close();
    }

    public boolean salvarIdSessao(String id) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.setex("idSessao", 43200, id);
        jedis.close();
        return true;
    }

    public String buscarIdSessao() {
        Jedis jedis = new Jedis("localhost", 6379);
        String aux = jedis.get("idSessao");
        jedis.close();
        return aux;
    }

    public void salvarCompra(Compra compra) {
        daoGrafo.salvarRelacionamentos(compra);
        daoMongo.salvarCompra(compra);
    }

    public List<Produto> buscarProdutosSugeridos(Produto produto) {
        List<Produto> produtos = new ArrayList<>();
        Map<Long, Integer> idProdutos = daoGrafo.buscarRelacionamentos(produto);
        if (idProdutos != null) {
            long id1 = 0, id2 = 0, id3 = 0, id4 = 0;
            int p = 0, s = 0, t = 0, q = 0;
            for (Long id : idProdutos.keySet()) {
                int qtd = idProdutos.get(id);
                if (qtd > p) {
                    p = qtd;
                    id1 = id;
                } else if (qtd <= p && qtd > s) {
                    s = qtd;
                    id2 = id;
                } else if (qtd <= s && qtd > t) {
                    t = qtd;
                    id3 = id;
                } else if (qtd <= t && qtd > q) {
                    q = qtd;
                    id4 = id;
                }
            }
            if (p != 0) {
                produtos.add(daoJpa.buscar(id1, Produto.class));
            }
            if (s != 0) {
                produtos.add(daoJpa.buscar(id2, Produto.class));
            }
            if (t != 0) {
                produtos.add(daoJpa.buscar(id3, Produto.class));
            }
            if (q != 0) {
                produtos.add(daoJpa.buscar(id4, Produto.class));
            }
        } else {
            List<Produto> produtosLoja = listarProdutos();
            try {
                for (int i = 0; i < 4; i++) {
                    produtos.add(produtosLoja.get(i));
                }
            } catch (IndexOutOfBoundsException e) {
                return produtos;
            }

        }
        return produtos;
    }
}
