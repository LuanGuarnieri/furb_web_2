package furb.web.rest_api_comandas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import furb.web.rest_api_comandas.model.ComandaProdutoEntity;

public interface ComandaProdutoRepository extends JpaRepository<ComandaProdutoEntity, Long> {

}
