package com.srm.empresax.emprestimos.domain.repository;

import com.srm.empresax.emprestimos.domain.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
