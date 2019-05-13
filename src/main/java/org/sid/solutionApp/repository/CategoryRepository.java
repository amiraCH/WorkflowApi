package org.sid.solutionApp.repository;

import java.awt.print.Pageable;
import java.util.Optional;

import org.sid.solutionApp.model.WorkflowCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<WorkflowCategory, Long>{
	Page<WorkflowCategory>findByIdWorkflow(long idWorkflow,Pageable pageable);
	Optional<WorkflowCategory> findByIdAndIdWorkflow(Long idCat, Long idWork);
}

