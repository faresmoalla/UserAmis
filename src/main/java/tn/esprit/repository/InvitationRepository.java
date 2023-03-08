package tn.esprit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entity.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

}
