package com.gs.equilibramais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gs.equilibramais.model.Funcionario_info;

@Repository
public interface Funcionario_infoRepository extends JpaRepository<Funcionario_info, Long>{

}
