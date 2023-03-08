package tn.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entity.User;
import tn.esprit.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;


	@PostMapping("/add-User")
	@ResponseBody
	public void addUser(@RequestBody User r)
	{
		  userService.ajouterUser(r);

	}


	@GetMapping("/listUser")
	@ResponseBody
	public List<User> listUser(){
		return userService.ListUser();
	}

	@DeleteMapping("/deleteUser/{idUser}")
	@ResponseBody
	public void deleteUser(@PathVariable("idUser") Long idUser){
		userService.supprimerUser(idUser);
	}


	@PutMapping("/modifierUser/{idUser}")
	@ResponseBody
	public void modifierUser(@RequestBody User r,@PathVariable("idUser") Long idUser){
		userService.updateUser(r,idUser);
	}

	@GetMapping("/getUser/{ididUser}")
	@ResponseBody
	public User getUsersByiD(@PathVariable("ididUser") Long ididUser){
		return userService.getUsernById(ididUser);
	}
}
