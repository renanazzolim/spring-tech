package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Person;
import com.example.demo.model.Telephone;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.exception.OnenessCpfException;
import com.example.demo.service.exception.OnenessTelephoneException;
import com.example.demo.service.exception.TelephoneNotFoundException;
import com.example.demo.service.impl.PersonServiceImpl;

/**
 * @author Renan
 * @date 22/12/2018
 */
@RunWith(SpringRunner.class)
public class PersonServiceTest {

	private static final String NAME = "Renan Azzolim";
	private static final String CPF = "04969215928";
	private static final String DDD = "041";
	private static final String NUMBER = "996048125";

	@MockBean
	private PersonRepository personRepository;

	private PersonService sut;

	private Person person;

	private Telephone tel;

	@Before
	public void setUp() throws Exception {
		sut = new PersonServiceImpl(personRepository);

		person = new Person(NAME, CPF);
		tel = new Telephone(DDD, NUMBER);
		person.setTelephones(Arrays.asList(tel));

		when(personRepository.findByCpf(CPF)).thenReturn(Optional.empty());
		when(personRepository.findByTelephoneDddAndTelephoneNumber(DDD, NUMBER))
			.thenReturn(Optional.empty());
	}

	@Test
	public void shouldSavePersonOnRepository() throws Exception {
		sut.save(person);
		verify(personRepository).save(person);
	}

	@Test(expected = OnenessCpfException.class)
	public void shouldNotSaveTwoPersonWithSameCpf() throws Exception {
		when(personRepository.findByCpf(CPF)).thenReturn(Optional.of(person));

		sut.save(person);
	}

	@Test(expected = OnenessTelephoneException.class)
	public void shouldNotSaveTwoPersonWithSameTelephone() throws Exception {
		when(personRepository.findByTelephoneDddAndTelephoneNumber(DDD, NUMBER))
			.thenReturn(Optional.of(person));

		sut.save(person);
	}
	
	@Test(expected = TelephoneNotFoundException.class)
	public void mustReturnNotFoundExceptionWhenDoesNotExistPersonWithTelephoneDddAndNumber() throws Exception {
		sut.searchByTelephone(tel);
	}
	
	@Test
	public void shouldSearchPersonByTelephoneDddAndNumber() throws Exception {
		when(personRepository.findByTelephoneDddAndTelephoneNumber(DDD, NUMBER))
			.thenReturn(Optional.of(person));
		
		Person personTest = sut.searchByTelephone(tel);
		
		verify(personRepository).findByTelephoneDddAndTelephoneNumber(DDD, NUMBER);		
		assertThat(personTest).isNotNull();
		assertThat(personTest.getName()).isEqualTo(NAME);
		assertThat(personTest.getCpf()).isEqualTo(CPF);
	}

}
