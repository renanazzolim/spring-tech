package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.Person;

/**
 * @author Renan
 * @date 22/12/2018
 */
public interface PersonRepository {

	public Person save(Person person);

	public Optional<Person> findByCpf(String cpf);

	public Optional<Person> findByTelephoneDddAndTelephoneNumber(String ddd, String number);

}
