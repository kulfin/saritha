
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.FilterSevices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

System.out.println("Filter drop down");
FilterSevices filterSevices=new FilterSevices();
ProjectServices prjctScr=new ProjectServices();
String flag=request.getParameter("flag");
System.out.println("flag---->"+flag);

if(flag.equalsIgnoreCase("getInstructionOnProject")){
	
	String project=request.getParameter("project");
	//System.out.println("Project --->"+Project);
	String result=filterSevices.getInstructionOnProject(project);
	
	if(result.equalsIgnoreCase(Constants.NO_DATA))
	{
		out.println("   ");
	}else
	{
		out.println(result);
	}
	
}
else
	if(flag.equals("storesFromProject")){
		
		String project=request.getParameter("project");
		
		//System.out.println("Project --->"+Project);
		
		String result=filterSevices.getStoresOnProject(project);
		System.out.println("result storesFromProject ---->"+result);
		
		if(result.equalsIgnoreCase(Constants.NO_DATA)){
			out.println("<select style=\"width: 150px;\" id=\"visit_store_name\" onchange=\"getStoresDetails();\">");
			out.println("<option value=\"NODATA\">NO DATA</option>");
			out.println("</select>");
		}
		else{
		
		String[] row_seperator=result.split(Constants.rowSeperator);
		
		out.println("<select style=\"width: 150px;\" id=\"visit_store_name\" onchange=\"getStoresDetails();\">");
		out.println("<option value=\"SELECT\">SELECT</option>");
		for(int i=0;i<row_seperator.length;i++){
			String[] column_seperator=  row_seperator[i].split(Constants.columnSeperator);
			out.println("<option value="+column_seperator[1]+">"+column_seperator[0]+"</option>");
		}
		out.println("</select>");
		}
	}

	else
		if(flag.equals("get_fd_hub")){
			
			
			
			
			
			String result=filterSevices.getFdHub();
			System.out.println("result storesFromProject ---->"+result);
			
		
			
			String[] row_seperator=result.split(Constants.rowSeperator);
			
			out.println("<select style=\"width: 160px;\" id=\"fd_hub_select\" >");
			out.println("<option value=\"SELECT\">SELECT</option>");
			for(int i=0;i<row_seperator.length;i++){
				String[] column_seperator=  row_seperator[i].split(Constants.columnSeperator);
				out.println("<option value="+column_seperator[1]+">"+column_seperator[1]+"</option>");
			}
			out.println("</select>");
			}

		else
			if(flag.equals("update_store_detail")){
			
				System.out.println(" update store detail");
				String outData=request.getParameter("storeId")+Constants.columnSeperator+
				request.getParameter("address")+Constants.columnSeperator+
				request.getParameter("contactName")+Constants.columnSeperator+
				request.getParameter("contactPhone")+Constants.columnSeperator+
				request.getParameter("hubName")+Constants.columnSeperator+
				
				request.getParameter("storeCode")+Constants.columnSeperator+
				request.getParameter("tsiName")+Constants.columnSeperator+
				request.getParameter("tsiPhone");
			
				
				System.out.println(" out data for service is "+outData);
				
				
			int result=prjctScr.updateStoreDetail(outData);
			out.println(result);
							}
		
else
if(flag.equals("getDivisionOnHub")){
	
	String hub_id=request.getParameter("hub_id");
	
	System.out.println("hub_id --->"+hub_id);
	
	String result=filterSevices.getDivisionOnHub(hub_id);
	
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 150px;\" id=\"fd_div\" >");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 150px;\" id=\"fd_div\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] region_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+region_column[1]+">"+region_column[0]+"</option>");
	}
	out.println("</select>");
	}	
}
else
if(flag.equals("UnitPass")){
	
	String unit_height=request.getParameter("unit_height");
	String unit_depth=request.getParameter("unit_depth");
	String ProjectElementID=request.getParameter("ProjectElementID");
	
	System.out.println("unit_height --->"+unit_height);
	System.out.println("unit_depth --->"+unit_depth);
	System.out.println("ProjectElementID --->"+ProjectElementID);
	
	ProjectServices project=new ProjectServices();
	String unit_h=project.unit_retrieve(unit_height);
	String unit_d=project.unit_retrieve(unit_depth);
	
	String becm_es=project.selectProjectElementsData(ProjectElementID);
	System.out.println("becm_es --->  "+becm_es);
	out.println(unit_h+"@!@"+unit_d+"@!@"+becm_es);
	
}
else
	if(flag.equals("updateMeasurementData")){
		
		String param = request.getParameter("param");
		String measureId = request.getParameter("measureId");
		System.out.println("param --->"+param);
		System.out.println("measureId --->"+measureId);
		ProjectServices project=new ProjectServices();
		String result=project.updateMeasurementData(param,measureId);
		
		out.println(result);
		
	}
else if(flag.equalsIgnoreCase("country")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	String result=filterSevices.getRegionOnCountry(data);
	System.out.println("result 1---->"+result);
	
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 115px;\" id=\"region_select\" >");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 115px;\" id=\"region_select\" onchange=\"return getStateOnRegion();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] region_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+region_column[1]+">"+region_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
//New Entry for element status
else if(flag.equalsIgnoreCase("element_status")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	String result=filterSevices.getAllElementStatus(data);
	System.out.println("result 1---->"+result);
	
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 115px;\" id=\"filter_element_status\" >");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"region_select\" >");
	//out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] region_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+region_column[1]+">"+region_column[0]+"</option>");
	}
	out.println("</select>");
	}
}

else if(flag.equalsIgnoreCase("countryAdd")){
	
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	String result=filterSevices.getRegionOnCountry(data);
	System.out.println("result 1---->"+result);
	
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"region_name\" >");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{	
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"region_name\" onchange=\"getStateOnRegionAdd();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] region_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+region_column[1]+">"+region_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("countryUpdate")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	String result=filterSevices.getRegionOnCountry(data);
	System.out.println("result 1---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"region_name_u\" >");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"region_name_u\" onchange=\"getStateOnRegionUpdate();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] region_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+region_column[1]+">"+region_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("region")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getStateOnRegion(data);
	System.out.println("result 2---->"+result);
	
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 115px;\" id=\"state_select\">");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{

	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 115px;\" id=\"state_select\" onchange=\"return getCityOnState();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] state_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+state_column[1]+">"+state_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("regionAdd")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getStateOnRegion(data);
	System.out.println("result 2---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"state_name\">");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"state_name\" onchange=\"getCityOnStateAdd();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] state_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+state_column[1]+">"+state_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("regionUpdate")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getStateOnRegion(data);
	System.out.println("result 2---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"state_name_u\">");
		out.println("<option value=\"NO DATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"state_name_u\" onchange=\"getCityOnStateUpdate();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] state_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+state_column[1]+">"+state_column[0]+"</option>");
	}
	out.println("</select>");
	}
}

else if(flag.equalsIgnoreCase("state")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getCityOnState(data);
	System.out.println("result 3---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 115px;\" id=\"city_select\" >");
		out.println("<option value=\"NO DATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 115px;\" id=\"city_select\" onchange=\"return getTownOnCity();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] city_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+city_column[1]+">"+city_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("stateAdd")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getCityOnState(data);
	System.out.println("result 3---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"city_name\" >");
		out.println("<option value=\"NO DATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"city_name\" onchange=\"getTownOnCityAdd();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] city_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+city_column[1]+">"+city_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("stateUpdate")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getCityOnState(data);
	System.out.println("result 3---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"city_name_u\" >");
		out.println("<option value=\"NO DATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	
	
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"city_name_u\" onchange=\"getTownOnCityUpdate();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] city_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+city_column[1]+">"+city_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("city")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getTownOnCity(data);
	System.out.println("result 4---->"+result);
	
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 115px;\" id=\"town_select\" >");
		out.println("<option value=\"NO DATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 115px;\" id=\"town_select\" onchange=\"return getAreaOnTown();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] town_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+town_column[1]+">"+town_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("cityAdd")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getTownOnCity(data);
	System.out.println("result 4---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"town_name\" >");
		out.println("<option value=\"NO DATA\">NO DATA</option>");
		//out.println("<option value=\"NA\">NA</option>");
		out.println("</select>");
	}
	else{	
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"town_name\" onchange=\"getAreaOnTownAdd();\">");
	//out.println("<option value=\"NA\">NA</option>");
	
	for(int i=0;i<column_seperator.length;i++){
		String[] town_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+town_column[1]+">"+town_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("cityUpdate")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getTownOnCity(data);
	System.out.println("result 4---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 100px;\" id=\"town_name_u\" >");
		out.println("<option value=\"NO DATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"town_name_u\" onchange=\"getAreaOnTownUpdate();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] town_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+town_column[1]+">"+town_column[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("town")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getAreaOnTown(data);
	System.out.println("result 5---->"+result);
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 115px;\" id=\"area_select\" onchange=\"getStoresOnArea();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	//out.println("<option value=\"NA\">NA</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] area_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+area_column[1]+">"+area_column[0]+"</option>");
	}
	out.println("</select>");
}
else if(flag.equalsIgnoreCase("townAdd")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getAreaOnTown(data);
	System.out.println("result 5---->"+result);
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"area_name\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
//	out.println("<option value=\"NA\">NA</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] area_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+area_column[1]+">"+area_column[0]+"</option>");
	}
	out.println("</select>");
}
else if(flag.equalsIgnoreCase("townUpdate")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getAreaOnTown(data);
	System.out.println("result 5---->"+result);
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 100px;\" id=\"area_name_u\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] area_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+area_column[1]+">"+area_column[0]+"</option>");
	}
	out.println("</select>");
}
else if(flag.equalsIgnoreCase("clientFromProject")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	String result=filterSevices.getProjectOnClient(data);
	System.out.println("result clientFromProject ---->"+result);
	
	String[] row_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 150px;\" id=\"Project_select\" onchange=\"getBrandsOnProjects();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	
	for(int i=0;i<row_seperator.length;i++){
		String[] column_seperator=row_seperator[i].split(Constants.columnSeperator);
		
		out.println("<option value="+column_seperator[1]+">"+column_seperator[0]+"</option>");
	}
	out.println("</select>");
	
}
else if(flag.equalsIgnoreCase("getBrandsOnProjects")){
	String data=request.getParameter("data");
	System.out.println("data---->"+data);
	
	ProjectServices projectServices=new ProjectServices();
	
	String result=projectServices.getBrandFromProjectId(data);
	System.out.println("result getBrandsOnProjects ---->"+result);
	
	if(result.equals(Constants.NO_DATA)){
		out.println("<select style=\"width: 150px;\" id=\"brand_select\" onchange=\"getElementOnBrands();\">");
		out.println("<option value=\"NODATA\">NO DATA</option>");	
		out.println("</select>");
	}
	else{	
	String[] row_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 150px;\" id=\"brand_select\" onchange=\"getElementOnBrands();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<row_seperator.length;i++){
		String[] column_seperator= row_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+column_seperator[1]+">"+column_seperator[0]+"</option>");
	}
	out.println("</select>");
	}
}
else if(flag.equalsIgnoreCase("getElementOnBrands")){
	//System.out.println("getElementOnBrands called");
	
	String brand=request.getParameter("brand");
	System.out.println("brand---->"+brand);
	/* if(brand.equalsIgnoreCase("SELECT")){
		out.println("SELECT BRAND");		
	} */
	String project=request.getParameter("project");
	//System.out.println("Project---->"+Project);
	
	ProjectServices projectServices=new ProjectServices();
	
	String result=projectServices.getElementFromBrand(brand,project);
	System.out.println("result getElementOnBrands ---->"+result);
	if(result.equals(Constants.NO_DATA)){
//		out.println("<select style=\"width: 150px;\" id=\"element_select\" onchange=\"getComponentMaterialOnElement();\">");
		out.println("<select style=\"width: 150px;\" id=\"element_select\">");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>"); 	
		
		
	}
	else{	
	String[] row_data=result.split(Constants.rowSeperator);
		
	out.println("<select style=\"width: 150px;\" id=\"element_select\">");
//	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<row_data.length;i++){
		String[] columnData= row_data[i].split(Constants.columnSeperator);
		out.println("<option value="+columnData[1]+">"+columnData[0]+"</option>");
	}
	out.println("</select>"); 
	}
}
else if(flag.equalsIgnoreCase("getElementComponent")){
	String brand=request.getParameter("brand");
	
	String project=request.getParameter("project");
	
	ProjectServices projectServices=new ProjectServices();
	
	String result=projectServices.getComponentFromBrand(brand,project);
	System.out.println("result getElementComponent ---->"+result);
	if(result.equals(Constants.NO_DATA)){

		out.println("<select style=\"width: 150px;\" id=\"component_id\">");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>"); 	
	}
	else{	
	String[] row_data=result.split(Constants.rowSeperator);
	out.println("<select style=\"width: 150px;\" id=\"component_id\">");
	for(int i=0;i<row_data.length;i++){
		String[] columnData= row_data[i].split(Constants.columnSeperator);
		out.println("<option value="+columnData[1]+">"+columnData[0]+"</option>");
	}
	out.println("</select>"); 
	}
}

else if(flag.equalsIgnoreCase("getComponentMaterialOnElement")){
	String brandID=request.getParameter("brand");
	System.out.println("brand---->"+brandID);
	
	String elementID=request.getParameter("element");
	System.out.println("element---->"+elementID);
	
	String ProjectID=request.getParameter("Project");
	System.out.println("Project---->"+ProjectID);
	
	ProjectServices projectServices=new ProjectServices();
	
	String result=projectServices.getComponentMaterialOnElement(ProjectID,brandID,elementID);
	System.out.println("result getComponentMaterialOnElement ---->"+result);
	out.println(result);
	return ;
	
}
else if(flag.equalsIgnoreCase("storesFromArea")){
	
	String area=request.getParameter("area");
	System.out.println("data---->"+area);
	String Project=request.getParameter("Project");
	System.out.println("Project---->"+Project);
	
	
	String result=filterSevices.getStoresOnArea(area,Project);
	System.out.println("result storesFromArea ---->"+result);
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		out.println("<select style=\"width: 150px;\" id=\"visit_store_name\" onchange=\"getStoresDetails();\">");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	
	String[] row_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 150px;\" id=\"visit_store_name\" onchange=\"getStoresDetails();\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<row_seperator.length;i++){
		String[] column_seperator=  row_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+column_seperator[1]+">"+column_seperator[0]+"</option>");
	}
	out.println("</select>");
	}
}else if(flag.equalsIgnoreCase("storesDetails")){
	
	String store=request.getParameter("store");
	System.out.println("store---->"+store);
	
	
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.getStoresDetails(store);
	out.println(result);
	

}
else
if(flag.equals("getFDApprovalMgr")){
	
	String result=filterSevices.getFDApprovalMgrName();
	
	if(result.equalsIgnoreCase(Constants.NO_DATA)){
		
		out.println("<select style=\"width: 150px;\" id=\"fd_FDMgr\" >");
		out.println("<option value=\"NODATA\">NO DATA</option>");
		out.println("</select>");
	}
	else{
	String[] column_seperator=result.split(Constants.rowSeperator);
	
	out.println("<select style=\"width: 150px;\" id=\"fd_FDMgr\">");
	out.println("<option value=\"SELECT\">SELECT</option>");
	for(int i=0;i<column_seperator.length;i++){
		String[] FDMgr_column= column_seperator[i].split(Constants.columnSeperator);
		out.println("<option value="+FDMgr_column[0]+">"+FDMgr_column[1]+"</option>");
	}
	out.println("</select>");
	}	
	
}

if(flag.equalsIgnoreCase("projCodeAndQty")){
	String prjectId=request.getParameter("projName");
	System.out.println("prjectId==="+prjectId);
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.getprojCodeWithQty(prjectId);
	System.out.println("result======="+result);
	out.println(result);
}



%>