package fi.haagahelia.demo.domain;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;
import fi.haagahelia.demo.domain.AppUser;
import fi.haagahelia.demo.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public UserDetailServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);

        AppUser currentUser = user.orElseThrow(() -> new UsernameNotFoundException("User not found:" + username));

        return new User(currentUser.getUsername(), currentUser.getPassword(),
                AuthorityUtils.createAuthorityList(currentUser.getRole()));

    }
}
