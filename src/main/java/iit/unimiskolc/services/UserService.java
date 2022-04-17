package iit.unimiskolc.services;


import iit.unimiskolc.domain.SellerImpl;
import iit.unimiskolc.domain.UserDetailsImp;
import iit.unimiskolc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }


    public String getPassByEmail(String email){
        return userRepo.getPasswordByEmail(email);
    }

    public boolean registerUser(SellerImpl user){ return userRepo.registerNewUser(user);}

    public SellerImpl findByEmail(String email){
        return userRepo.findByEmail(email);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SellerImpl user = findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImp(user);
    }
}
