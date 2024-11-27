package bzu.gradproj.optivet.backend.model.factory;


//import bzu.gradproj.optivet.backend.model.entity.entity.User_2;

//import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.model.security.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collections;

public class UserFactory {

    public static SecurityUser create(Client client) {
        // Converts a User entity
        // into a SecurityUser object,
        // which is used by Spring Security

        return new SecurityUser(
                client.getId(),
//      user2.getUsername(),
                client.getEmail(),// we use email here, which is treated as the username in SecurityUser
                //we take it from user entity as an email, but then take it from securityUser as username

                client.getPassword(),
                client.getLastPasswordReset(),
                AuthorityUtils.createAuthorityList("ROLE_CLIENT") // Role for clients

//                AuthorityUtils.commaSeparatedStringToAuthorityList(user2.getAuthorities())
                //// Converts comma-separated authorities into a list of GrantedAuthority objects
        );
    }

    public static SecurityUser create(User user) {
        return new SecurityUser(
                user.getId(),
                user.getEmail(), // Email as username
                user.getPassword(),
                user.getLastPasswordReset(),
                AuthorityUtils.createAuthorityList("ROLE_USER") // Role for users
        );
    }

}

/*

In this process,

you've already mapped
the email from User
to the username in SecurityUser,

which is necessary since Spring Security will still work with getUsername():

*/
