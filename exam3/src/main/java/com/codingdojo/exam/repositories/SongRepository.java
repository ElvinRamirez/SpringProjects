package com.codingdojo.exam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codingdojo.exam.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
	List<Song> findAll();
	
	Boolean existsByTitle(String title);
	
	
	
	//JPQL QUEry
	@Transactional
	@Modifying
	@Query(value = "UPDATE Songs s SET s.title = ?1, s.genre = ?2, s.lyrics = ?3,"
	        + " s.times_edited = coalesce(s.times_edited,0) + 1 WHERE s.id = ?4", nativeQuery = true)
	void increaseTimesEdited(String title, String genre, String lyrics, Long id );
	
	//paramenter needs to be an id. Cannot cast instance of song to an Integer equivalent to the id
	
	//@Query(value = "UPDATE Songs s SET s.times_edited = times_edited + 1 WHERE s.id = :id", nativeQuery = true)
    //public Integer increaseTimesEdited(Long id);
}
