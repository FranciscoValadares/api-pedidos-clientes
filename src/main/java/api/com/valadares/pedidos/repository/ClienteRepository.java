package api.com.valadares.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.com.valadares.pedidos.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
