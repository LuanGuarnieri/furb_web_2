package rest_api_comandas.pojos;

import rest_api_comandas.model.ComandaProdutoEntity;

public class ProdutoTransform {

    public static ProdutoPojo toPojo(ComandaProdutoEntity entity) {
        ProdutoPojo pojo = new ProdutoPojo();
        pojo.setId(entity.getId());
        pojo.setNome(entity.getNome());
        pojo.setPreco(entity.getPreco());
        return pojo;
    }

    public static ComandaProdutoEntity toEntity(ProdutoPojo pojo) {
        ComandaProdutoEntity entity = new ComandaProdutoEntity();
        entity.setId(pojo.getId());
        entity.setNome(pojo.getNome());
        entity.setPreco(pojo.getPreco());
        return entity;
    }
}
