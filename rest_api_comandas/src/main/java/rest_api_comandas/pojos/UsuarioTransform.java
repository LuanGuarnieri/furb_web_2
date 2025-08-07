package rest_api_comandas.pojos;

import rest_api_comandas.model.UsuarioEntity;

public class UsuarioTransform {

    public static UsuarioPojo toPojo(UsuarioEntity entity) {
        UsuarioPojo pojo = new UsuarioPojo();
        pojo.setIdUsuario(entity.getId());
        pojo.setNomeUsuario(entity.getNome());
        pojo.setTelefoneUsuario(entity.getTelefone());
        return pojo;
    }

    public static UsuarioEntity toEntity(UsuarioPojo pojo) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(pojo.getIdUsuario());
        entity.setNome(pojo.getNomeUsuario());
        entity.setEmail(pojo.getEmailUsuario());
        entity.setSenha(pojo.getSenhaUsuario());
        entity.setTelefone(pojo.getTelefoneUsuario());
        return entity;
    }
}