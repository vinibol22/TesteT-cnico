package com.br.attornatus;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TesteAttornatusApplicationTests {
	private AutoCloseable closeable;

	@BeforeEach
	public void openMocks() {
		closeable = MockitoAnnotations.openMocks(this);

	}
	@AfterEach
	public void realeseMocks() throws Exception {
		closeable.close();
	}
	

}
