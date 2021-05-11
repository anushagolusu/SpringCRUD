package com.spring.exp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class StudentDAO {

	
	JdbcTemplate template;
	private PlatformTransactionManager transactionManager;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public int saveStudent(final StudentBean studentBean){
		System.out.println("*** saveStudent ***");
		
		/*String query  = "insert into student_master"
				+ "(student_id, student_name, student_surname, student_attendance, student_dob) values ("+
						+ studentBean.studentId
						+ ",'"+studentBean.name+"'"
						+ ",'"+studentBean.surName+"'"
						+ ","+(long) studentBean.percentage
						+ ",'"+new java.sql.Date(0)
					+"')"; */
						
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
			// TODO Auto-generated method stub
				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream("E:\\SriRama.jpg");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				File file = new File("E:\\ConcentForm.txt");
				FileReader fileReader = null;
				try {
					fileReader = new FileReader(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			String query ="insert into student_master values(?, ?, ?, ?, ?, ?, ?);";
			final PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, studentBean.studentId);
			preparedStatement.setString(2, studentBean.name);
			preparedStatement.setString(3, studentBean.surName);
			//preparedStatement.setBinaryStream(4, fileInputStream, fileInputStream.available());
			//preparedStatement.setCharacterStream(5, fileReader, (int)file.length());
			//preparedStatement.setCharacterStream(5, null, 0);
			preparedStatement.setLong(4, (long) studentBean.percentage);
			preparedStatement.setDate(5, new java.sql.Date(0));
			try {
				preparedStatement.setBinaryStream(6, fileInputStream, fileInputStream.available());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			preparedStatement.setCharacterStream(7, fileReader, (int)file.length());
			return preparedStatement;
		}
		};
		return template.update(preparedStatementCreator);

	}
	//@Transactional
	public int update(StudentBean studentBean){  
		System.out.println("*** update ***");
		 TransactionDefinition def = new DefaultTransactionDefinition();
	     // TransactionStatus status = transactionManager.getTransaction(def);
	    String sql="update student_master set student_name = '" +studentBean.name+"', student_surname = '"+studentBean.surName+"' where student_id = "+studentBean.studentId;
	    int res = 0;
	    try{
	    	res = template.update(sql); 
    		//transactionManager.commit(status);
	    }catch (Exception e) {
	    	//transactionManager.rollback(status);
		}
	    
	    return res;
	}    
	
	public int delete(int studentId){
		System.out.println("*** delete ***");
		String sql = "delete from student_master where student_id="+studentId+"";    
		return template.update(sql);
	}   
	
	public StudentBean getStudentById(int studentId){    
		System.out.println("*** getStudentById ***");
	    String sql="select * from student_master where student_id=?";    
	    //return template.queryForObject(sql, new Object[]{studentId},new BeanPropertyRowMapper<StudentBean>(StudentBean.class));    
		  return template.queryForObject(sql, new Object[]{studentId},new BeanPropertyRowMapper<StudentBean>(StudentBean.class));
	}    
	
	public List<StudentBean> getStudents(){    
		System.out.println("*** getStudents ***");
	    return template.query("select * from student_master",new RowMapper<StudentBean>(){    
	        @Override
			public StudentBean mapRow(ResultSet rs, int row) throws SQLException {    
	        	StudentBean e=new StudentBean();    
	            e.setStudentId(rs.getInt(1));    
	            e.setName(rs.getString(2));    
	            e.setSurName(rs.getString(3));    
	            e.setPercentage(rs.getFloat(4));    
	            e.setDob(rs.getDate(5));  
	            return e;    
	        }    
	    });    
	}	
}