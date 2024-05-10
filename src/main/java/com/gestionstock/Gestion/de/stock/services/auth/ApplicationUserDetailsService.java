package com.gestionstock.Gestion.de.stock.services.auth;

import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.model.Utilisateur;
import com.gestionstock.Gestion.de.stock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByMail(mail).orElseThrow(() ->
                new EntityNotFoundException("Aucun Utilisateur avec l'email fourni", ErrorCodes.UTILISATEUR_NOT_FOUND)
        );

        return new User(utilisateur.getMail(), utilisateur.getMotDePasse(), Collections.emptyList());
    }
}

