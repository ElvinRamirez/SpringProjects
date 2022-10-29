package com.codingdojo.exam.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.codingdojo.exam.models.Song;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.repositories.SongRepository;
import com.codingdojo.exam.repositories.UserRepository;

@Service
public class SongService {
	
	@Autowired
	private SongRepository songRepo;
	@Autowired
	private UserRepository userRepo;
	
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
	
	//Relationship between user & song
	public User songUserRel(Long userId, Long songId) {
	    User thisUser = userRepo.findUserById(userId);
	    Song thisSong = songRepo.findSongById(songId);
	    thisUser.getSongs().add(thisSong);
	    userRepo.save(thisUser);
	    return thisUser;
	    
	}
	


	
	
}
