
<%@page import="java.util.Properties"%>
<%@page import="javax.mail.*,javax.mail.internet.*"%>

<%
String s=request.getParameter("subject");
String m=request.getParameter("message");
String to="ramki345@gmail.com";

  Properties props = new Properties();
  props.put("mail.smtp.host", "smtp.gmail.com");
  props.put("mail.smtp.socketFactory.port", "465");
  props.put("mail.smtp.socketFactory.class",
        	"javax.net.ssl.SSLSocketFactory");
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.port", "465");
 
  Session session1 = Session.getDefaultInstance(props,
   new javax.mail.Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication("ramki345@gmail.com","hi2hello");
   }
  });
 
//compose message
  try {
  
   MimeMessage message = new MimeMessage(session1);
   message.setFrom(new InternetAddress("sareneeru94@gmail.com"));
   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
   message.setSubject(s);
   message.setText(m);
   
   //send message
  Transport.send(message);
  session.setAttribute("contact_status","true");
  response.sendRedirect("contact.jsp");
  
   
  } catch (MessagingException e) {throw new RuntimeException(e);}
 
 
%>