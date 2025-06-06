package br.com.fiap.triaurban.repository;

import br.com.fiap.triaurban.model.PontoDistribuicao;
import br.com.fiap.triaurban.projection.PontoComTotalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PontoDistribuicaoRepository extends JpaRepository<PontoDistribuicao, Long> {
    @Query(value = """
    SELECT 
        pd.nome AS nome_ponto, 
        e.logradouro || ', ' || e.numero || ' - ' || e.bairro || ', ' || e.cidade || ' - ' || e.uf || ', ' || e.cep AS endereco_ponto,
        SUM(ei.quantidade) AS total_itens
    FROM ponto_distribuicao pd
    JOIN estoque_item ei ON ei.ponto_distribuicao_id = pd.id
    JOIN endereco e ON pd.endereco_id = e.id
    GROUP BY pd.nome, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, e.cep
    ORDER BY total_itens DESC
    """, nativeQuery = true)
    List<PontoComTotalProjection> encontrarPontosComMaisItens();


}
