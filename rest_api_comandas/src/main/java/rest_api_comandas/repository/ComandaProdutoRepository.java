package rest_api_comandas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rest_api_comandas.model.ComandaProdutoEntity;

public interface ComandaProdutoRepository extends JpaRepository<ComandaProdutoEntity, Long> {

	List<ComandaProdutoEntity> findByComandaId(Long comandaId);
	
}
