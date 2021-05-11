<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
        <h1>Add New Student</h1>  
       <form:form method="post" action="save">    
       <table >    
        <tr>  
        <td></td>    
         <tr>    
          <td>studentId : </td>   
          <td><form:input path="studentId"  /></td>  
         </tr> 
         <tr>    
          <td>Name : </td>   
          <td><form:input path="name"  /></td>  
         </tr>    
         <tr>    
          <td>surName : </td>    
          <td><form:input path="surName" /></td>  
          
          
         </tr>   
       <%--   <tr>    
          <td>percentage :</td>    
          <td><form:input path="percentage" /></td>  
         </tr>   
         <tr>    
          <td>dob :</td>    
          <td><form:input path="dob" /></td>  
         </tr>  --%>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form>    