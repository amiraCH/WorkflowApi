package org.sid.solutionApp.model;
import java.io.Serializable;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;

@Entity
public class WorkflowCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929484560187859943L;
	@Id @GeneratedValue
	private Long idCategory;
	private String name;
	private String logo;
	private int status; //enabled
	private WorkflowCategory parentCategory;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="id_workflow" , nullable = false)
	@OnDelete(action =OnDeleteAction.CASCADE)
	@JsonIgnore
	private Workflow workflow;
	
	
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	
	public WorkflowCategory(String name, int status, WorkflowCategory parentCategory) {
		super();
		this.name = name;
		this.status = status;
		this.parentCategory = parentCategory;
	}
	public WorkflowCategory() {
		super();
	}
	public Long getIdCategory() {
		return idCategory;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public WorkflowCategory getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(WorkflowCategory parentCategory) {
		this.parentCategory = parentCategory;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	
	
	
}
