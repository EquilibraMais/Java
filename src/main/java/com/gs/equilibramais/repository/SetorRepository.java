package com.gs.equilibramais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gs.equilibramais.model.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>{

}
