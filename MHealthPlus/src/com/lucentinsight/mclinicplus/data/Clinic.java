package com.lucentinsight.mclinicplus.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Clinic {

private String name;
private String address;
private double lat;
private double lang;
private String contactNo;
private Map<String, Doctor> doctors = new HashMap<String, Doctor>();
private Set<String> specializations = new HashSet<String>();

public void addSpecialization(String specialization){
	this.specializations.add(specialization);
}

public Set<String> getSpecialization() {
	return specializations;
}

public void setSpelications(Set<String> specialization) {
	this.specializations = specialization;
}

public Map<String, Doctor> getDoctors() {
	return doctors;
}

public void addDoctor(String specialization,Doctor doctor){
	doctors.put(specialization, doctor);
}
public void setDoctors(Map<String, Doctor> doctors) {
	this.doctors = doctors;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public double getLat() {
	return lat;
}
public void setLat(double lat) {
	this.lat = lat;
}
public double getLang() {
	return lang;
}
public void setLang(double lang) {
	this.lang = lang;
}
public String getContactNo() {
	return contactNo;
}
public void setContactNo(String contactNo) {
	this.contactNo = contactNo;
}


}
