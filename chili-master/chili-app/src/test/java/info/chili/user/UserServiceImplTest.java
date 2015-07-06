/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 *
 * @author ayalamanchili
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldSaveNewUser() {
        final User savedUser = stubRepositoryToReturnUserOnSave();
        final User user = new User();
        final User returnedUser = userService.save(user);
        // verify repository was called with user
        verify(userRepository, times(1)).save(user);
        assertEquals("Returned user should come from the repository", savedUser, returnedUser);
    }

    private User stubRepositoryToReturnUserOnSave() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        return user;
    }

}