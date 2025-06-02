package br.com.fiap.triaurban.repository;

import br.com.fiap.triaurban.model.EstoqueItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueItemRepository extends JpaRepository<EstoqueItem, Long> {
}
