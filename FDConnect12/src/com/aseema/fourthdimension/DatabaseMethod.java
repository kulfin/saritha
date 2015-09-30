package com.aseema.fourthdimension;

import java.sql.ResultSet;

public abstract class DatabaseMethod {

	abstract ResultSet Select_State();
	//abstract ResultSet getAllMaterial();
	abstract String Insert_into_State(String country_name,String region_name,String state_name);
	//abstract String getCountry_drop_down();
	abstract ResultSet getRegion_drop_down_based_on_country(int country_id);
	abstract String Update_State_Details(String country_name,String region_name,String state_name,int state_id);
	abstract String Delete_State_Details(int State_id);
	abstract ResultSet City_Details();
	abstract String Insert_City(String country_name,String region_name,String state_name,String city_name);
	abstract String Update_City_Details(String country_name,String region_name,String state_name,String city_name,int city_id);
	abstract String Delete_City_Details(int city_id);
	abstract ResultSet Document_Type_Master_Details(); 
	abstract String Insert_Document_Type(String document_type_name,String description,String owner);
	abstract String Update_Document_Type(int document_type_id,String document_type_name,String description,String owner );
	abstract String Delete_Document_Type_Master(int document_type_id);
	abstract ResultSet Document_Status_Master();
	abstract String Insert_Document_Status_Master(String document_status_name);
	abstract String Update_Document_Status_Master(int doc_status_id ,String document_status_name);
	abstract String Delete_Document_Status_Master(int doc_status_id);	
	abstract String Update_Document_Master(int doc_id,String doc_type_name,String doc_name);
	abstract String Delete_Document_Master(int doc_id);
	abstract ResultSet Town_Details();
	abstract ResultSet Select_Area();

}
