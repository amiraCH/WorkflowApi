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
public class Workflow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7088980469962242321L;
	@Id @GeneratedValue
	private Long idWorkflow;
	private String name;
	private String description;
	private int status; //enabled
	private Workflow workflow;
	
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="id_category" , nullable = false)
	@OnDelete(action =OnDeleteAction.CASCADE)
	@JsonIgnore
	private WorkflowCategory category;
	
	
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
    public Workflow(String name, String description, int status) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
	}
	public Workflow() {
		super();
	}
	public Long getidWorkflow() {
		return idWorkflow;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	
	
	
	
}
