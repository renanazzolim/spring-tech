package com.example.demo.model;

/**
 * @author Renan
 * @date 22/12/2018
 */
public class Telephone {

	private String ddd;

	private String number;

	public Telephone() {
	}

	public Telephone(String ddd, String number) {
		this.ddd = ddd;
		this.number = number;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
