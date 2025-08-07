package rest_api_comandas.pojos;

import java.util.List;
import java.util.stream.Collectors;

import rest_api_comandas.model.ComandaEntity;
import rest_api_comandas.model.ComandaProdutoEntity;
import rest_api_comandas.model.UsuarioEntity;

public class ComandaTransform {

    public static ComandaPojo toPojo(ComandaEntity comanda) {
        ComandaPojo pojo = new ComandaPojo();
        pojo.setId(comanda.getId());
        pojo.setIdUsuario(comanda.getUsuario().getId());
        pojo.setNomeUsuario(comanda.getUsuario().getNome());
        pojo.setTelefoneUsuario(comanda.getUsuario().getTelefone());

        List<ProdutoPojo> produtos = comanda.getProdutos().stream().map(produto -> {
            ProdutoPojo p = new ProdutoPojo();
            p.setId(produto.getId());
            p.setNome(produto.getNome());
            p.setPreco(produto.getPreco());
            return p;
        }).collect(Collectors.toList());

        pojo.setProdutos(produtos);
        return pojo;
    }

    public static ComandaEntity toEntity(ComandaPojo pojo, UsuarioEntity usuario) {
        ComandaEntity comanda = new ComandaEntity();
        comanda.setUsuario(usuario);

        List<ComandaProdutoEntity> produtos = pojo.getProdutos().stream().map(obj -> {
            ComandaProdutoEntity produto = new ComandaProdutoEntity();
            produto.setNome(obj.getNome());
            produto.setPreco(obj.getPreco());
            produto.setComanda(comanda);
            return produto;
            
        }).collect(Collectors.toList());

        comanda.setProdutos(produtos);
        return comanda;
    }
}
