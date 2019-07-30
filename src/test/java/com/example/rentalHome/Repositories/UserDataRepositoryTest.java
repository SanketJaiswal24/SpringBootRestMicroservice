package com.example.rentalHome.Repositories;

import com.example.rentalHome.Domains.UserCredentials;
import com.example.rentalHome.Domains.UserData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDataRepositoryTest
{
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserSelfcredentialsRepository selfcredentialsRepository;

    @Test
    public void saveUserTest()
    {
        UserData user = new UserData(1L,"SanketDineshJaiswal","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        UserData userData = this.userDataRepository.save(user);
        assertThat(userData.getEmail()).isEqualTo("sanket.jaiswal2409@gmail.com");
        assertThat(userData.getUserId()).isEqualTo(1);
        assertThat(userData.getContact()).isEqualTo("8959926364");
    }

    @Test
    public void saveUserSingInTest()
    {
        UserCredentials userCredentials = new UserCredentials(1L,"sanket.jaiswal2409@gmail.com","password@123","8959926364");
        UserCredentials jsonInputUserCredentials = this.selfcredentialsRepository.save(userCredentials);
        assertThat(jsonInputUserCredentials.getEmailId()).isEqualTo("sanket.jaiswal2409@gmail.com");
    }

    @Test
    public void getAllUserTest()
    {

     UserData user1 = new UserData(1L,"SanketDineshJaiswal","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
     UserData user2 = new UserData(2L,"LokiJiiiiiiiiiiiiiii","8995667888","Loki.marvel@gmail.com","Asgard","2016-01-19","M","Loki-name");
     this.userDataRepository.save(user1);
     this.userDataRepository.save(user2);
     List<UserData> userData = this.userDataRepository.getAllUser();
     assertThat(userData.get(0).getName()).isEqualTo("SanketDineshJaiswal");
     assertThat(userData.get(0).getEmail()).isEqualTo("sanket.jaiswal2409@gmail.com");
     assertThat(userData.get(1).getName()).isEqualTo("LokiJiiiiiiiiiiiiiii");
    }

    @Test
    public void searchUserTest()
    {
        UserData user1 = new UserData(1L,"SanketDineshJaiswal","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        this.userDataRepository.save(user1);
        UserData result = this.userDataRepository.searchUserId(user1.getUserId());
        assertThat(user1.getUserId()).isEqualTo(1L);
        assertThat(user1.getEmail()).isEqualTo("sanket.jaiswal2409@gmail.com");
        assertThat(user1.getName()).isEqualTo("SanketDineshJaiswal");
    }

    @Test
    public void deleteUserTest()
    {
        UserData user2 = new UserData(2L,"LokiJiiiiiiiiiiiiiii","8995667888","Loki.marvel@gmail.com","Asgard","2016-01-19","M","Loki-name");
        UserData userData = new UserData(1L,"SanketDineshJaiswal","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        this.userDataRepository.save(userData);
        this.userDataRepository.save(user2);
        this.userDataRepository.delete(userData.getUserId());
        /*UserData userData1 = this.userDataRepository.searchUserId(userData.getUserId());
        assertThat(userData1.getUserId()).isZero();*/
    }

    @Test
    public void updateUserTest()
    {
        UserData olduser = new UserData(1L,"LokiJiiiiiiiiiiiiiii","8995667888","Loki.marvel@gmail.com","Asgard","2016-01-19","M","Loki-name");
        UserData userData = new UserData(1L,"SanketDineshJaiswal","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        this.userDataRepository.save(olduser);
        UserData result = this.userDataRepository.save(userData);
        assertThat(result.getName()).isEqualTo("SanketDineshJaiswal");
        assertThat(result.getEmail()).isEqualTo("sanket.jaiswal2409@gmail.com");
    }

    @Test
    public void userLoginTest()
    {
        UserData userData = new UserData(1L,"SanketDineshJaiswal","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        this.userDataRepository.save(userData);
        UserCredentials userCredentials = new UserCredentials(1L,"sanket.jaiswal2409@gmail.com","password@123","8959926364");
        this.selfcredentialsRepository.save(userCredentials);
        UserData result = this.userDataRepository.getByEmail(userCredentials.getEmailId());
        assertThat(result.getEmail()).isEqualTo("sanket.jaiswal2409@gmail.com");
        assertThat(result.getName()).isEqualTo("SanketDineshJaiswal");
    }
}
