package com.app.trade.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.app.trade.Exception.UserExceptions;
import com.app.trade.dao.UserDao;
import com.app.trade.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private JWTService jwtService;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private UserDao userDao;

    private User testUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testUser = new User();
        testUser.setUserName("testUser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
    }

    // Test for registerUser - Empty User Name or Email
    @Test(expected = UserExceptions.class)
    public void testRegisterUser_EmptyFields() {
        testUser.setUserName("");
        testUser.setEmail("");
        userService.registerUser(testUser);
        verify(userDao, never()).saveUser(any(User.class));
    }

    // Test for registerUser - User Already Exists
    @Test(expected = UserExceptions.class)
    public void testRegisterUser_UserAlreadyExists() {
        when(userDao.checkExistingUser(testUser.getUserName(), testUser.getEmail())).thenReturn(true);
        userService.registerUser(testUser);
        verify(userDao, never()).saveUser(any(User.class));
    }

    // Test for registerUser - Successful Registration
    @Test
    public void testRegisterUser_Successful() {
        when(userDao.checkExistingUser(testUser.getUserName(), testUser.getEmail())).thenReturn(false);
        when(userDao.saveUser(any(User.class))).thenReturn(testUser);

        User registeredUser = userService.registerUser(testUser);
        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUserName());
        assertEquals("test@example.com", registeredUser.getEmail());
        verify(userDao, times(1)).saveUser(testUser);
    }

    // Test for verify - Successful Authentication
    @Test
    public void testVerify_Successful() {
        when(userDao.fetchUserByEmail(testUser.getEmail())).thenReturn(testUser);
        Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.isAuthenticated()).thenReturn(true);
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mockAuth);
        when(jwtService.generateToken(testUser.getUserName())).thenReturn("jwt-token");

        String token = userService.verify(testUser);
        assertEquals("jwt-token", token);
    }

    // Test for verify - Failed Authentication
    @Test
    public void testVerify_FailedAuthentication() {
        when(userDao.fetchUserByEmail(testUser.getEmail())).thenReturn(testUser);
        Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.isAuthenticated()).thenReturn(false);
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mockAuth);

        String result = userService.verify(testUser);
        assertEquals("fail", result);
    }
}
