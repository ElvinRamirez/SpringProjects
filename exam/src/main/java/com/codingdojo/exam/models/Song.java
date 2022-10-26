package com.codingdojo.exam.models;


import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name="songs")
public class Song {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(min=3, message="Title is required")
    private String title;
	
	@NotBlank
	@Size(min=3, message="Genre is required")
    private String genre;
	
	@NotBlank
	@Size(min=5,message="Lyrics required")
    private String lyrics;
	
	
	private Integer timesEdited=0;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date updatedAt;
	
	//Relationship
	  
   // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="user_id")
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            //name is the same on both models. The name of the middle/join table.
            //the naming convention is both model names separated by underscore
        name = "users_songs", 
        //joinColums represents the "local" model id
        joinColumns = @JoinColumn(name = "song_id"), 
        //inverseJoinColums references the "other" model id/foreign key
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    
  //create a list referencing the other model
    private List<User> users;
    //private User user;
    
    public Song() {
        super();
    }
   

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
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


	public Integer getTimesEdited() {
		return timesEdited;
	}
	
	
	public void setTimesEdited(Integer timesEdited) {
		this.timesEdited = timesEdited;
	}


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

    
}
