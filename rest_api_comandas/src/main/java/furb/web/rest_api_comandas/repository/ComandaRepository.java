package furb.web.rest_api_comandas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import furb.web.rest_api_comandas.model.ComandaEntity;

public interface ComandaRepository extends JpaRepository<ComandaEntity, Long> {

}
