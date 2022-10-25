package bankSystem;

import java.security.MessageDigest;

public class Person {
	private String name;
	private String surname;
	private char gender;
	private int age;
	private String uniqueID;
	
	Person(String name, String surname, char gender, int age){
		this.setName(name);
		this.setSurname(surname);
		setGender(gender);
		setAge(age);
		
		try {
			uniqueID = genUniqueID();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String genUniqueID() throws Exception {
		String key = name+surname+gender+String.valueOf(age);
		
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte hash[] = algorithm.digest(key.getBytes("UTF-8"));
		
		StringBuilder texto = new StringBuilder();
		for (byte b : hash) {
			texto.append(String.format("%02X", 0xFF & b));
		}
		
		return texto.toString();
	}
	  
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	  
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.name = surname;
	}

	public char getGender() {
		return gender;
	}
	
	private void setGender(char gender){
		this.gender = Character.toLowerCase(gender);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUniqueID() {
		return uniqueID;
	}
}
