package com.servletcrud.service;
import com.servletcrud.service.IncrementInteger;

import com.servletcrud.service.UserLogin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

import javax.enterprise.inject.Produces;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
	

	
		
		@WebServlet("/uploadFileServlet")
		public class UploadFileServlet extends HttpServlet{
		
		private final static String bucket_name= "mytestbucket23275";
		private final static String file_path= "D:\\vasu\\awsupload\\";
		private final static String Config_path= "D:\\vasu\\awsupload\\config";
		private  String key_name= "AKIARO";
		
		
		private InstrideJndi jndi = null;
		private static final long serialVersionUID = 1L;




		


		public void init(ServletConfig servletConfig) throws ServletException {
			super.init(servletConfig);
			jndi = new InstrideJndi("UploadFile");
		}
		
		  
		 
		   @POST
		   @Path("/upload")
		   @javax.ws.rs.Produces(MediaType.TEXT_HTML)
		   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		{
			PrintWriter out = response.getWriter();
			 JSONArray jArrayImage = new JSONArray();
			 JSONObject arrayObjImage = new JSONObject();
			 
			 
			 
			 JSONArray jArrayImg = new JSONArray();
			 JSONObject arrayObjImg = new JSONObject();
			 HttpSession session=request.getSession();
			 
			 response.setContentType("text/html");
		       
			String filename=request.getParameter("filename");	
			session.setAttribute("filename", filename);
			String file_path="D:\\vasu\\awsupload\\" + filename;
			out.println("full filepath is:::" +file_path);
			 PreparedStatement ps=null;
			 int id=0;
			
		     
			  ResultSet rs=null;
			  DataSource ds=null;
			  Connection con=null;
			  
			  
			
			
				
				  Long lastTime = 0L; 
				  Long now = System.currentTimeMillis();
				  if (now >  lastTime)
					lastTime = now;
				  Long currentLong = ++lastTime;
				  int count =  currentLong.intValue();
				  int positiveCount = Math.abs(count);
				  
				  List<Integer> countList = new ArrayList<Integer>();
				  countList.add(positiveCount);
				  
				  int maxCount = Collections.max(countList);
				  
				  out.println("current value of count is:::" +positiveCount);
				  key_name=key_name+maxCount;
				  
				  if(key_name.length()>=12) {
				  
				  key_name=key_name.substring(6,12)+maxCount;
				  }else {
				  key_name=key_name+maxCount;
				  }
				  
				  
				  
				  
				  out.println("value of key name is:::" +key_name);
				 
		    
		    
			out.println("Uploading  to S3 bucket:"+ file_path + bucket_name);
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSCredentialsProvider() {
				
				@Override
				public void refresh() {
				}
				@Override
				public AWSCredentials getCredentials() {
					AWSCredentials aWSCredentials = new AWSCredentialsImpl();  
					return aWSCredentials;
				}
			}).withRegion(Regions.US_EAST_1).build();			
			
			try {
				
				
				File fileLocation = new File(file_path);
				String imageDescription="Inserting Image";
				
				 String saveFile= "C:/receivedPhoto/"+filename; 
				 s3.putObject(new PutObjectRequest(bucket_name, filename, new File(saveFile)).withCannedAcl(CannedAccessControlList.PublicRead));
				
				
			  //  s3.putObject(bucket_name, key_name, fileLocation);
			    ds=jndi.getDataSource();
			    con=ds.getConnection();
			    
			    String path=new String();
			    String fileLocationAWS="https://mytestbucket23275.s3.amazonaws.com/" +filename;
			    
			    
			    
			    System.out.println("aws file location is:::" +fileLocationAWS);
			    session.setAttribute("fileLocationAWS",fileLocationAWS);
			    
			   Integer loginId=(Integer)session.getAttribute("id");
			   System.out.println("login id for the said user is:::" +loginId);
					
						con.setAutoCommit(false);	
						
						  ps=con.prepareStatement("select path from description d where d.login_id='"+loginId+"'");   
						  rs=ps.executeQuery();
						  
						  List<String> listImages=new ArrayList<String>();
						  List<String> listImageAWS = new ArrayList<String>();
						  
						while(rs.next()) {
							path="https://mytestbucket23275.s3.amazonaws.com/"+rs.getString(1);
							listImages.add(path);
							 listImageAWS.add(fileLocationAWS);
						}
						 
						
						  session.setAttribute("listImages", listImages);
						 
						
					         ps=con.prepareStatement("insert into description (about, path, login_id) values(?,?,?)");   
							 ps.setString(1,imageDescription);  
							 ps.setString(2,filename);  
							 ps.setInt(3,  loginId);
							 ps.executeUpdate();  
							con.commit();
							 
							  try {
								  
								  arrayObjImage.put("path", path);
								  jArrayImg.put(arrayObjImage);
								  
								arrayObjImage.put("listImageAWS", listImageAWS.toString());
								arrayObjImage.put("imageDescription",imageDescription);
								arrayObjImage.put("filename", filename);
								jArrayImage.put(arrayObjImage);
							} catch (JSONException e) {
								
								e.printStackTrace();
							}
						     
							  String imagePaths=jArrayImg.toString();
							  String imageString = jArrayImage.toString();
							 
                              session.setAttribute("imagePaths", imagePaths);
							  session.setAttribute("jArrayImage", imageString);           
							  RequestDispatcher rD = request.getRequestDispatcher("../album.jsp");
							  rD.forward(request, response);
						  
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
			    catch (AmazonServiceException e) {
			    out.println(e.getMessage());
			   
			}finally {
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(session!=null) {
					session.invalidate();
				}
			}
		
		}
		
		}
		}
		
		



