
package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.model.Telephone;

/**
 * @author Renan
 * @date 22/12/2018
 */
public interface PersonService {

	public Person save(Person person) throws Exception;

	public Person searchByTelephone(Telephone tel) throws Exception;

}
