package rest_api_comandas.pojos;

import rest_api_comandas.model.ComandaProdutoEntity;

public class ProdutoTransform {

    public static ProdutoPojo toPojo(ComandaProdutoEntity entity) {
        ProdutoPojo dto = new ProdutoPojo();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        return dto;
    }

    public static ComandaProdutoEntity toEntity(ProdutoPojo dto) {
        ComandaProdutoEntity entity = new ComandaProdutoEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        return entity;
    }
}
