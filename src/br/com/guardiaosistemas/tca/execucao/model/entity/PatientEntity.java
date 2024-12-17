package br.com.guardiaosistemas.tca.execucao.model.entity;

import java.io.Serializable;
import java.util.Date;

public class PatientEntity implements Serializable {

	private static final long serialVersionUID = 6161594385532224082L;
	
	private String name;
	private Integer age;
	private Integer instruction;
	private String gender; // M = Male / F = Female
	private String hand; // L = Left / R = Right
	private Date testDate = new Date();
	private Integer executionTime; // 5, 10 or 15 minutes
	
	public PatientEntity() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getInstruction() {
		return instruction;
	}
	public void setInstruction(Integer instruction) {
		this.instruction = instruction;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHand() {
		return hand;
	}
	public void setHand(String hand) {
		this.hand = hand;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Integer getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Integer executionTime) {
		this.executionTime = executionTime;
	}

	@Override
	public String toString() {
		return "PatientEntity [name=" + name + ", age=" + age + ", instruction=" + instruction + ", gender=" + gender
				+ ", hand=" + hand + ", testDate=" + testDate + ", executionTime=" + executionTime + "]";
	}

}
