package org.sid.solutionApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.sid.solutionApp.exception.ResourceNotFoundException;
import org.sid.solutionApp.model.Workflow;
import org.sid.solutionApp.model.WorkflowCategory;
import org.sid.solutionApp.repository.CategoryRepository;
import org.sid.solutionApp.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WorkflowController {
	@Autowired
	WorkflowRepository workflowRepository;
	
	// Get All workflows 
		@GetMapping("/workflows")
		public List<Workflow> getAllWorkflows() {
		    return workflowRepository.findAll();
		}
		
		// Create a new Workflow 
		@PostMapping("/workflows")
		public Workflow createWorkflow(@Valid @RequestBody Workflow workflow) {
		    return workflowRepository.save(workflow);
		}
		
		// Get a Single workflow 
		@GetMapping("/workflows/{id}")
		public Workflow getWorkflowById(@PathVariable(value = "id") Long idWorkflow) {
		    return workflowRepository.findById(idWorkflow)
		            .orElseThrow(() -> new ResourceNotFoundException("Workflow", "id", idWorkflow));
		}
		
		// Update a workflow 
		@PutMapping("/workflows/{id}")
		public Workflow updateWorkflow(@PathVariable(value = "id") Long idWorkflow,
		                                        @Valid @RequestBody Workflow workflowDetails) {

			Workflow workflow = workflowRepository.findById(idWorkflow)
		            .orElseThrow(() -> new ResourceNotFoundException("Workflow", "id", idWorkflow));

			workflow.setName(workflowDetails.getName());
			workflow.setStatus(workflowDetails.getStatus());

			Workflow updatedWorkflow = workflowRepository.save(workflow);
		    return updatedWorkflow;
		}
		
		// Delete a workflowCategory 
		@DeleteMapping("/workflows/{id}")
		public ResponseEntity<?> deleteWorkflows(@PathVariable(value = "id") Long idWorkflow) {
			Workflow workflow = workflowRepository.findById(idWorkflow)
					 .orElseThrow(() -> new ResourceNotFoundException("Workflow", "id", idWorkflow));

			 workflowRepository.delete(workflow);

		    return ResponseEntity.ok().build();
		}
	
	
}
