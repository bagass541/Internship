package com.bank.bankcredits.entities;

import java.time.LocalDate;

import com.bank.bankcredits.entities.enums.SexType;
import com.bank.bankcredits.exceptions.IncorrectUserName;
import com.bank.bankcredits.exceptions.IncorrectUserSecondName;

import lombok.Getter;

@Getter
public class User {

	private long id;
	
	private String name;
	
	private String secondName;
	
	private SexType sex;
	
	private LocalDate birthday;

	public User(long id, String name, String secondName, SexType sex, LocalDate birthday) throws IncorrectUserName, IncorrectUserSecondName {
		this.id = id;
		
		if(name.split("\\w+").length > 1) {
			throw new IncorrectUserName();
		} else {
			this.name = name;
		}
		
		if(secondName.split("\\w+").length > 1) {
			throw new IncorrectUserSecondName();
		} else {
			this.secondName = secondName;
		}

		this.sex = sex;
		this.birthday = birthday;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) throws IncorrectUserName {
		if(name.split("\\w+").length > 1) {
			throw new IncorrectUserName();
		} else {
			this.name = name;
		}
	}

	public void setSecondName(String secondName) throws IncorrectUserSecondName {
		if(secondName.split("\\w+").length > 1) {
			throw new IncorrectUserSecondName();
		} else {
			this.secondName = secondName;
		}
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}
