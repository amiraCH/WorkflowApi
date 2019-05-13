package org.sid.solutionApp.repository;


import org.sid.solutionApp.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long>{
	
}
