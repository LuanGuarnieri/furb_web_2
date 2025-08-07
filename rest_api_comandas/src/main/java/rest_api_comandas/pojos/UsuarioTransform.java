package rest_api_comandas.pojos;

import rest_api_comandas.model.UsuarioEntity;

public class UsuarioTransform {

    public static UsuarioPojo toPojo(UsuarioEntity entity) {
        UsuarioPojo dto = new UsuarioPojo();
        dto.setIdUsuario(entity.getId());
        dto.setNomeUsuario(entity.getNome());
        dto.setTelefoneUsuario(entity.getTelefone());
        return dto;
    }

    public static UsuarioEntity toEntity(UsuarioPojo dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(dto.getIdUsuario());
        entity.setNome(dto.getNomeUsuario());
        entity.setTelefone(dto.getTelefoneUsuario());
        return entity;
    }
}