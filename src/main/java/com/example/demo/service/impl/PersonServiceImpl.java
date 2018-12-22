package com.example.demo.service.impl;

import java.util.Optional;

import com.example.demo.model.Person;
import com.example.demo.model.Telephone;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import com.example.demo.service.exception.OnenessCpfException;
import com.example.demo.service.exception.OnenessTelephoneException;
import com.example.demo.service.exception.TelephoneNotFoundException;

/**
 * @author Renan
 * @date 22/12/2018
 */
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Person save(Person person) throws Exception {
		Optional<Person> optional = personRepository.findByCpf(person.getCpf());
		
		if (optional.isPresent()) {
			throw new OnenessCpfException();
		}
		final String ddd = person.getTelephones().get(0).getDdd();
		final String num = person.getTelephones().get(0).getNumber();
		optional = personRepository.findByTelephoneDddAndTelephoneNumber(ddd, num);
		
		if (optional.isPresent()) {
			throw new OnenessTelephoneException();
		}
		
		return personRepository.save(person);
	}

	@Override
	public Person searchByTelephone(Telephone tel) throws Exception {
		Optional<Person> optional = personRepository
				.findByTelephoneDddAndTelephoneNumber(tel.getDdd(), tel.getNumber());
		return optional.orElseThrow(() -> new TelephoneNotFoundException());
	}
	
	

}
