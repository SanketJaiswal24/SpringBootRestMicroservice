package com.example.rentalHome.Services;

import com.example.rentalHome.Domains.UserCredentials;
import com.example.rentalHome.Domains.UserData;
import com.example.rentalHome.Repositories.UserDataRepository;
import com.example.rentalHome.Repositories.UserSelfcredentialsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserDataServiceTest
{

    @Mock
    private UserDataRepository userDataRepository;

    @Mock
    private UserSelfcredentialsRepository userSelfcredentialsRepository;

    @InjectMocks
    private UserDataServiceImpl userDataService;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testgetAllUser()
    {
        List<UserData> userDataList = new ArrayList<UserData>();
        userDataList.add(new UserData(1L,"Sanket","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name"));
        userDataList.add(new UserData(2L,"Loki","8995667888","Loki.marvel@gmail.com","Asgard","2016-01-19","M","Loki-name"));
        userDataList.add(new UserData(3L,"Dvj","8778889911","thor.marvel@gmail.com","Asgard","2012-17-19","M","Thor-name"));
        when(userDataRepository.getAllUser()).thenReturn(userDataList);
        List<UserData> result = userDataService.showAllUser();
        assertEquals(3,result.size());
    }

    @Test
    public void testSearchUser()
    {
        UserData userData = new UserData(1L,"Sanket","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        when(userDataRepository.searchUserId(1L)).thenReturn(userData);
        UserData result = userDataService.searchUser(1L);
        //assertEquals(1L,result.getUserId());
        assertEquals("Sanket",result.getName());
        assertEquals("sanket.jaiswal2409@gmail.com",result.getEmail());
    }

    @Test
    public void testCreateUser()
    {
        UserData userData = new UserData(8L,"Sanket","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        when(userDataRepository.save(userData)).thenReturn(userData);
        UserData result = userDataService.createUser(userData);
        /*assertEquals(8L,result.getUserId());*/
        assertEquals("Sanket",result.getName());
        assertEquals("sanket.jaiswal2409@gmail.com",result.getEmail());
    }

    @Test
    public void testUserSingin()
    {
        UserCredentials userCredentials = new UserCredentials(Long.valueOf(1L),"sanket.jaiswal2409@gmail.com","password@123","8959926364");
        when(userSelfcredentialsRepository.save(userCredentials)).thenReturn(userCredentials);
        UserCredentials result = userDataService.userSingIn(userCredentials);
        assertEquals("sanket.jaiswal2409@gmail.com",result.getEmailId());
        assertEquals("password@123",result.getPassword());
    }

    @Test
    public void testUserLogIn()
    {
        UserData userData = new UserData(8L,"Sanket","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        UserCredentials userCredentials = new UserCredentials(Long.valueOf(8L),"sanket.jaiswal2409@gmail.com","password@123","8959926364");
        when(userDataRepository.getByEmail(userCredentials.getEmailId())).thenReturn(userData);
        UserData result = userDataService.userLogIn(userCredentials);
        assertEquals("sanket.jaiswal2409@gmail.com",result.getEmail());
        /*assertEquals(8L,result.getUserId());*/
        assertEquals("pic-name",result.getProfilePicture());
    }

    @Test
    public void testUpdateUser()
    {
        UserData userData = new UserData(8L,"Sanket","8959926364","sanket.jaiswal2409@gmail.com","118 Bhagat Singh Marge Sawer Road","2018-03-19","M","pic-name");
        when(userDataRepository.save(userData)).thenReturn(userData);
        UserData result = userDataService.updateUser(userData);
        assertEquals("Sanket",result.getName());
        assertEquals("sanket.jaiswal2409@gmail.com",result.getEmail());
    }

    @Test
    public void testDeleteUser()
    {
        userDataService.deleteUser(1L);
        verify(userDataRepository,times(1)).delete(1L);
    }


}
