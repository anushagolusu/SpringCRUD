<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
  
 
        <h1>Edit Student</h1>  
        <form:form method="POST" action="/SpringCRUD/editsave">    
        <table >    
      <%--   <tr>  
        <td></td>    
         <td><form:hidden  path="studentId" /></td>  
         </tr>  --%>  
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

           
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>   
        
       </form:form>    