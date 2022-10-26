package com.codingdojo.exam.models;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
//@SelectBeforeUpdate
@Table(name="songs")
//@IdClass(Song.class)
public class Song  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(min=3, message="Title is required")
    private String title;
	
	@NotBlank
	@Size(min=3, message="Genre is required")
    private String genre;
	
	
	@Column(columnDefinition="TEXT")
	@NotBlank
	@Size(min=5, message="Lyrics required")
    private String lyrics;
	

	private Integer timesEdited;
	
	//updatedBy field gets the id of the user that updated the Song and can show the name of the user related to the id. 
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long updatedBy = null;
	


    @Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date updatedAt;
	
	//Relationship
	  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    public Song() {
        super();
    }
    
    public Song(String title, String genre, String lyrics, Integer timesEdited) {
        this.title = title;
        this.genre = genre;
        this.lyrics = lyrics;
        this.timesEdited = timesEdited;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getTimesEdited() {
		return timesEdited;
	}

	public void setTimesEdited(int timesEdited) {
		this.timesEdited = timesEdited;
	}

	//public Long getUpdatedBy() {
	//    return updatedBy;
	//}

	//public void setUpdatedBy(Long updatedBy) {
	//    this.updatedBy = updatedBy;
	//}
    
}