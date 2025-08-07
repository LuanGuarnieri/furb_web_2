package rest_api_comandas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest_api_comandas.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	UsuarioEntity findByEmail(String email);
}
