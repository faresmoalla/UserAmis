package tn.esprit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entity.Invitation;
import tn.esprit.entity.User;
import tn.esprit.repository.InvitationRepository;
import tn.esprit.repository.UserRepository;

@Service
@Slf4j
public class InvitationSevice {
@Autowired
InvitationRepository InvitRepo;
@Autowired
JavaMailSender javaMailSender;
@Autowired
UserRepository userRepo;


public void SendInvitation(Invitation inv,Long idUser,Long idUser2 ) {
	User u =  userRepo.findById(idUser).orElse(null);
	User u2 =  userRepo.findById(idUser2).orElse(null);
	
	inv.setUser(u2);
	inv.setEtat("en attente");
	
	inv.setUtilis(u);
	SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	simpleMailMessage.setFrom("schoolesprit1@gmail.com");
	//simpleMailMessage.setTo(r.getUser().getEmailUser());
	simpleMailMessage.setTo(u.getEmailUser());
	simpleMailMessage.setSubject("Un utilisateur vous a envoyé une invitaion");
	simpleMailMessage.setText("Un utilisateur vous a envoyé une invitaion");
	javaMailSender.send(simpleMailMessage);

	
	
	  InvitRepo.save(inv);
	
}

public void accepterInvitation(Long IdInvitation) {
	Invitation u =  InvitRepo.findById(IdInvitation).orElse(null);

	u.setEtat("accepté");

	InvitRepo.save(u);
}


public void RefuserInvi(Long IdInvitation) {
	Invitation u =  InvitRepo.findById(IdInvitation).orElse(null);

	u.setEtat("refusé");

	InvitRepo.save(u);
}



public List<User> ListAmi(Long IdUser){
	List<User> listAmis = new ArrayList<User>();
	User u =  userRepo.findById(IdUser).orElse(null);

	for(Invitation a: u.getInvitations()) {
		if(a.getEtat().equals("accepté")) {
			
			listAmis.add(a.getUser());
		}
		
	}
	return listAmis;
	
	
}

	
}
