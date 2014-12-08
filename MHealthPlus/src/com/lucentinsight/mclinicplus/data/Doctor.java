package com.lucentinsight.mclinicplus.data;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

	private String name;
	private String qualification;
	private String specialization;
	private List<Schedule> schdules=new ArrayList<Schedule>();
	
	
	public List<Schedule> getSchdules() {
		return schdules;
	}
	public void setSchdules(List<Schedule> schdules) {
		this.schdules = schdules;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public void addSchedule(Schedule schedule){
		schdules.add(schedule);
	}
}
