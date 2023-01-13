package com.regis.historicoprecoback.repository;

import com.regis.historicoprecoback.model.Preco;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM preco WHERE produto_id = ?1", nativeQuery = true)
    void deleteAllByProdutoId(String id);

}
