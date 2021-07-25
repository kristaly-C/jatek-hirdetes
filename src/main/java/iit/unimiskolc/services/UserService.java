package iit.unimiskolc.services;


import iit.unimiskolc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }


    public String getPassByEmail(String email){
        return userRepo.getPasswordByEmail(email);
    }
}
