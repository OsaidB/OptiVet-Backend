package bzu.gradproj.optivet.backend.service.auth;

import bzu.gradproj.optivet.backend.exception.NoUserFoundException;
//import bzu.gradproj.optivet.backend.model.entity.entity.User_2;
//import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.factory.UserFactory;
import bzu.gradproj.optivet.backend.repository.ClientRepo;
//import bzu.gradproj.optivet.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepo clientRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = this.clientRepo.findByEmail(email)
                .orElseThrow(() -> new NoUserFoundException(String.format("No client found with email '%s'.", email)));
        return UserFactory.create(client); // Assuming UserFactory supports Client
    }

}
