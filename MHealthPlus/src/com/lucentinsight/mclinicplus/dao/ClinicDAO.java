package com.lucentinsight.mclinicplus.dao;


import java.util.Map;

import com.lucentinsight.mclinicplus.data.Clinic;

public interface ClinicDAO {
	
	public Map<String, Clinic> getAllClinics(String json);

}
