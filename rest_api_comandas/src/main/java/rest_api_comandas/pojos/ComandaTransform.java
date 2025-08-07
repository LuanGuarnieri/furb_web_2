package rest_api_comandas.pojos;

import java.util.List;
import java.util.stream.Collectors;

import rest_api_comandas.model.ComandaEntity;
import rest_api_comandas.model.ComandaProdutoEntity;
import rest_api_comandas.model.UsuarioEntity;

public class ComandaTransform {

    public static ComandaPojo toPojo(ComandaEntity comanda) {
        ComandaPojo dto = new ComandaPojo();
        dto.setId(comanda.getId());
        dto.setIdUsuario(comanda.getUsuario().getId());
        dto.setNomeUsuario(comanda.getUsuario().getNome());
        dto.setTelefoneUsuario(comanda.getUsuario().getTelefone());

        List<ProdutoPojo> produtos = comanda.getProdutos().stream().map(produto -> {
            ProdutoPojo p = new ProdutoPojo();
            p.setId(produto.getId());
            p.setNome(produto.getNome());
            p.setPreco(produto.getPreco());
            return p;
        }).collect(Collectors.toList());

        dto.setProdutos(produtos);
        return dto;
    }

    public static ComandaEntity toEntity(ComandaPojo dto, UsuarioEntity usuario) {
        ComandaEntity comanda = new ComandaEntity();
        comanda.setUsuario(usuario);

        List<ComandaProdutoEntity> produtos = dto.getProdutos().stream().map(pojo -> {
            ComandaProdutoEntity produto = new ComandaProdutoEntity();
            produto.setNome(pojo.getNome());
            produto.setPreco(pojo.getPreco());
            produto.setComanda(comanda);
            return produto;
            
        }).collect(Collectors.toList());

        comanda.setProdutos(produtos);
        return comanda;
    }
}
