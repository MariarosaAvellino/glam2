package bcsoft.it.glam.service;


import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtenteRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> user = userRepository.findByUsername(username);
        Utente user1 = (user).orElseThrow(() -> {
            throw new UsernameNotFoundException("User non è trovato");
        });
        return new org.springframework.security.core.userdetails.User(user1.getUsername(), user1.getPassword(),
                user1.isEnable(), true, true, true, getAuthorities("User"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}
