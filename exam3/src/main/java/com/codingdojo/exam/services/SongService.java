package com.codingdojo.exam.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.codingdojo.exam.models.Song;
import com.codingdojo.exam.repositories.SongRepository;

@Service
public class SongService {
	
	@Autowired
	private SongRepository songRepo;

	
	public Song findById(Long id) {
		
		Optional<Song> result = songRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
	}
	

	public List<Song> all() {
		return songRepo.findAll();
	}
	
	
	public Song update(Song song) {
		return songRepo.save(song);
	}
	
	
	public void increment(String title, String genre, String lyrics, Long id ) {
	       //Takes an instance of a song
	        
	      songRepo.increaseTimesEdited(title,genre,lyrics,id);
	      
	}
	
	
	public Song create(Song song) {
		return songRepo.save(song);
	}
	
	
	public void destroy(Long id) {
		songRepo.deleteById(id);
	}
	
}
