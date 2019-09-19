package com.example.rentalHome.Services;

import com.example.rentalHome.Domains.UserCredentials;
import com.example.rentalHome.Domains.UserData;
import com.example.rentalHome.Repositories.UserDataRepository;
import com.example.rentalHome.Repositories.UserSelfcredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService
{
     @Autowired
     private UserDataRepository userDataRepository;

     @Autowired
     private UserSelfcredentialsRepository userSelfcredentialsRepository;

     @Override
     public UserCredentials userSingIn(UserCredentials userCredentials){ return userSelfcredentialsRepository.save(userCredentials); }

     @Override
     public UserData userLogIn(UserCredentials userlogin) { return userDataRepository.getByEmail(userlogin.getEmailId()); }

     @Override
     public List<UserData> showAllUser()  {
        return userDataRepository.getAllUser();
    }

     @Override
     public UserData createUser(UserData user) {
        return  userDataRepository.save(user);
    }

     @Override
     public UserData searchUser(Long id) {
        return userDataRepository.searchUserId(id);
    }

     @Override
     public UserData updateUser(UserData user) {
        return userDataRepository.save(user);
    }

     @Override
     public void deleteUser(Long id) {
        userDataRepository.delete(id);
    }
}
