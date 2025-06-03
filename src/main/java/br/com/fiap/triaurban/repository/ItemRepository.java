package br.com.fiap.triaurban.repository;

import br.com.fiap.triaurban.model.CategoriaItemEnum;
import br.com.fiap.triaurban.model.Item;
import br.com.fiap.triaurban.projection.ItemComEstoqueProjection;
import br.com.fiap.triaurban.projection.ItemPontoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = """
    SELECT i.nome AS nome, i.categoria AS categoria, ei.quantidade AS quantidade
    FROM estoque_item ei
    JOIN item i ON ei.item_id = i.id
    WHERE ei.ponto_distribuicao_id = :idPonto
    """, nativeQuery = true)
    List<ItemPontoProjection> buscarItensPorPonto(@Param("idPonto") Long idPonto);

    @Query(value = """
    SELECT 
        i.nome AS nome_item, 
        ei.quantidade, 
        pd.nome AS nome_ponto,
        e.logradouro || ', ' || e.numero || ' - ' || e.bairro || ', ' || e.cidade || ' - ' || e.uf || ', ' || e.cep AS endereco_ponto
    FROM item i
    JOIN estoque_item ei ON ei.item_id = i.id
    JOIN ponto_distribuicao pd ON ei.ponto_distribuicao_id = pd.id
    JOIN endereco e ON pd.endereco_id = e.id
    WHERE i.categoria = :categoria
    """, nativeQuery = true)
    List<ItemComEstoqueProjection> buscarItensPorCategoria(@Param("categoria") String categoria);



}
