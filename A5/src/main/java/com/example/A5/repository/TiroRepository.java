package com.example.A5.repository;

import com.example.A5.model.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TiroRepository extends JpaRepository<Tiro, Long> {
    List<Tiro> findByPartidaId(Long partidaId);
}
