package com.santosguilherme.register.service;

import com.santosguilherme.register.domain.User;
import com.santosguilherme.register.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UserServiceTest {


	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void should_register_user_successfully() {
		User userBeforeSave = new User(null,"Guilherme", "1234",
				LocalDate.of(1996,10, 24));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		Mockito.when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRepository).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);
	}

	@ParameterizedTest
	@ValueSource(ints = {2010, 1960})
	public void should_return_error_when_age_is_invalid(Integer year) {

		User user = new User(null, "Guilherme", "1234", LocalDate.of(year, 1, 31));

		var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.register(user));

		Mockito.verifyNoInteractions(userRepository);
		Assertions.assertEquals("idade não permitida", exception.getMessage());
	}

}
