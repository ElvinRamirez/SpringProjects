package com.codingdojo.exam.models;

import java.util.Date;
import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="Name is required!")
    @Size(min=3, max=30, message="Name must be between 3 and 30 characters")
    private String name;
    
    @NotBlank(message="Email is required!")
    @Size(min=3, max=40)
    @Email(message="Email requires at least 5 characters!")
    private String email;
    
    @NotBlank(message="Password is required!")
    @Size(min=8, max=128, message="Password is required!")
    private String password;
    
    @Transient
    @NotBlank(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password is required!")
    private String confirm;
    

    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    private Date updatedAt;
    
    //Relationship
  
    //@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            //name is the same on both models. The name of the middle/join table.
            //the naming convention is both model names separated by underscore
        name = "users_songs", 
        //joinColums represents the "local" model id
        joinColumns = @JoinColumn(name = "user_id"), 
        //inverseJoinColums references the "other" model id/foreign key
        inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    
    private List<Song> songs;

    public User() {}
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
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

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
    
    
}
