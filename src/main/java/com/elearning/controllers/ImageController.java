package com.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elearning.entities.Image;
import com.elearning.repositories.ImageRepository;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImageRepository imagerepository;
	
	@GetMapping("/{id}")
	public Image getImage(@PathVariable("id") Long id) {
		return imagerepository.findById(id).get();
	}
	
	@GetMapping("/All")
	public List<Image> getAll() {
		return imagerepository.findAll();
	}
	
	@PostMapping("/addImage")
	public Image ajouterImage(@RequestBody Image  image) {
		
		return imagerepository.save(image);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		imagerepository.deleteById(id);
		return "Image deleted";
	}
	
}
