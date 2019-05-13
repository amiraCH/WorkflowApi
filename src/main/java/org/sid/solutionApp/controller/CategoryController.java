package org.sid.solutionApp.controller;

import java.awt.print.Pageable;
import java.util.List;

import javax.validation.Valid;

import org.sid.solutionApp.exception.ResourceNotFoundException;
import org.sid.solutionApp.model.WorkflowCategory;
import org.sid.solutionApp.repository.CategoryRepository;
import org.sid.solutionApp.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;
	WorkflowRepository workflowRepository;
	
	// Get All workflows Category
	@GetMapping("/categories")
	public List<WorkflowCategory> getAllWorkflowsCat() {
	    return categoryRepository.findAll();
	}
	// Create a new Workflow Category
	@PostMapping("/categories")
	public WorkflowCategory createWorkflowCat(@Valid @RequestBody WorkflowCategory workflowcategory) {
	    return categoryRepository.save(workflowcategory);
	}
	// Get a Single workflow Category
	@GetMapping("/categories/{id}")
	public WorkflowCategory getWorkflowCatById(@PathVariable(value = "id") Long idCategory) {
	    return categoryRepository.findById(idCategory)
	            .orElseThrow(() -> new ResourceNotFoundException("WorkflowCategory", "id", idCategory));
	}
	// Update a workflow Category
	@PutMapping("/categories/{id}")
	public WorkflowCategory updateWorkflowCat(@PathVariable(value = "id") Long idCategory,
	                                        @Valid @RequestBody WorkflowCategory CategoryDetails) {

	    WorkflowCategory workflowCategory = categoryRepository.findById(idCategory)
	            .orElseThrow(() -> new ResourceNotFoundException("WorkflowCategory", "id", idCategory));

	    workflowCategory.setName(CategoryDetails.getName());
	    workflowCategory.setStatus(CategoryDetails.getStatus());

	    WorkflowCategory updatedCategory = categoryRepository.save(workflowCategory);
	    return updatedCategory;
	}
	
	// Delete a workflowCategory Category
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<?> deleteWorkflowsCat(@PathVariable(value = "id") Long idCategory) {
		 WorkflowCategory workflowCategory = categoryRepository.findById(idCategory)
				 .orElseThrow(() -> new ResourceNotFoundException("WorkflowCategory", "id", idCategory));

		 categoryRepository.delete(workflowCategory);

	    return ResponseEntity.ok().build();
	}
	//AMC************
	@GetMapping("/workflows/{idWorkflow}/categories")
    public Page<WorkflowCategory> getAllCategoriesByworkflowId(@PathVariable (value = "idWorkflow") Long idWorkflow,
                                                Pageable pageable) {
        return categoryRepository.findByIdWorkflow(idWorkflow, pageable);
    }
	
	@PutMapping("/workflows/{idWorkflow}/categories/{commentId}")
    public WorkflowCategory updateCategory(@PathVariable (value = "idWorkflow") Long idWorkflow,
                                 @PathVariable (value = "idCategory") Long commentId,
                                 @Valid @RequestBody WorkflowCategory workflowCategory) {
        if(!workflowRepository.existsById(idWorkflow)) {
            throw new ResourceNotFoundException("idWorkflow " + idWorkflow + " not found");
        }

        return CategoryRepository.findByIdWorkflow(idWorkflow).map(workflowCategory -> {
            workflowCategory.setName(categoryRequest.getNAme());
            return categoryRepository.save(workflowCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + idCategory + "not found"));
    }

    @DeleteMapping("/workflows/{idWorkflow}/categories/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "idWorkflow") Long idWorkflow,
                              @PathVariable (value = "idCategory") Long idCategory) {
        return categoryRepository.findByIdAndIdWorkflow(idCategory, idWorkflow).map(workflowCategory -> {
        	categoryRepository.delete(workflowCategory);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + idCategory + " and idWorkflow " + idWorkflow));
    }
}
