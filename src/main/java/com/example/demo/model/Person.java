package com.example.demo.model;

import java.util.List;

/**
 * @author Renan
 * @date 22/12/2018
 */
public class Person {

	private String name;

	private String cpf;

	private List<Address> addresses;

	private List<Telephone> telephones;

	public Person() {
	}

	public Person(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

}
