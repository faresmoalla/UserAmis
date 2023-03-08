package tn.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entity.Invitation;
import tn.esprit.entity.User;
import tn.esprit.service.InvitationSevice;
import tn.esprit.service.UserService;

@RestController
@RequestMapping("/invitation")
public class InvitationController {
	@Autowired
	UserService userService;
	@Autowired
	InvitationSevice inviService;
	
	
	@PostMapping("/send-invitation/{idUser1}/{idUser2}")
	@ResponseBody
	public void SendInvi(@RequestBody Invitation r,@PathVariable("idUser1") Long idUser1,@PathVariable("idUser2") Long idUser2)
	{
		inviService.SendInvitation(r,idUser1,idUser2);

	}
	@PutMapping("/accepterInvitation/{IdInvi}")
	@ResponseBody
	public void AccepterInvitation(@PathVariable("IdInvi") Long IdInvi)
	{
		inviService.accepterInvitation(IdInvi);

	}
	
	@PutMapping("/refuser-Invitation/{IdInvi}")
	@ResponseBody
	public void RefusserInvitation(@PathVariable("IdInvi") Long IdInvi)
	{
		inviService.RefuserInvi(IdInvi);

	}
	@GetMapping("/listAmis/{IdUser}")
	@ResponseBody
	public List<User> ListAmis(@PathVariable("IdUser") Long IdUser)
	{
		return inviService.ListAmi(IdUser);

	}
	
}
