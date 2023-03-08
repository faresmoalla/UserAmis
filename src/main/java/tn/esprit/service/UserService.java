package tn.esprit.service;


import tn.esprit.entity.User;
import tn.esprit.repository.UserRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
UserRepository userRepo;
@Autowired
JavaMailSender javaMailSender;


public void ajouterUser(User r) {
	
	userRepo.save(r);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			//simpleMailMessage.setTo(r.getUser().getEmailUser());
			simpleMailMessage.setFrom("schoolesprit1@gmail.com");

			simpleMailMessage.setTo("hajer.moumni@esprit.tn");
			simpleMailMessage.setSubject("Welcome");
			simpleMailMessage.setText("Welcome");
			javaMailSender.send(simpleMailMessage);
	
}


public List<User> ListUser(){
	return userRepo.findAll();
}


public void supprimerUser(Long idUser) {
	User r = userRepo.findById(idUser).orElse(null);
	
	userRepo.delete(r);
}
	
public void updateUser(User user, Long idUser) {
	
	User u= userRepo.findById(idUser).orElse(null);	
	
	u.setIdUser(idUser);
	u.setAdresse(user.getAdresse());
	u.setDateNaissance(user.getDateNaissance());
	u.setNomUser(user.getNomUser());
	u.setNumTel(user.getNumTel());
	u.setPrenomUser(user.getPrenomUser());
	u.setEmailUser(user.getEmailUser());
	userRepo.save(u);
	
}	


public User getUsernById(Long idUser) {
	return userRepo.findById(idUser).orElse(null);	
}













}
