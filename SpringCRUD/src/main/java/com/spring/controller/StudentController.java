
package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.exp.StudentBean;
import com.spring.exp.StudentDAO;

@Controller  
public class StudentController {

	@Autowired    
	
    StudentDAO dao;//will inject dao from XML file
	
	/*It displays a form to input data, here "command" is a reserved request attribute  
     *which is used to display object data into form  
     */    
	@RequestMapping("/studentform")    
    public String showform(Model m){    
        m.addAttribute("command", new StudentBean());  
        return "studentformjsp";   
    }    
	
	/*It saves object into database. The @ModelAttribute puts request data  
     *  into model object. You need to mention RequestMethod.POST method   
     *  because default request is GET*/    
	    
	@RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("studentBean") StudentBean studentBean){  
		//System.out.println("reqParamExp " +reqParamExp);
        dao.saveStudent(studentBean);    
        return "redirect:/viewstudent";//will redirect to viewemp request mapping    
    }    
	
	/*
	 * @RequestMapping(value="/save",method = RequestMethod.POST) public StudentBean
	 * saveTest(@ModelAttribute("studentBean") StudentBean studentBean){
	 * dao.saveStudent(studentBean); return new StudentBean();//will redirect to
	 * viewemp request mapping }
	 */ 
    
    /* It provides list of employees in model object */    
    @RequestMapping("/viewstudent")    
    public String viewemp(Model m){    
        List<StudentBean> list=dao.getStudents();    
        m.addAttribute("list",list);  
        return "viewstudent";    
    }   
    
    /* It displays object data into form for the given id.   
     * The @PathVariable puts URL data into variable.*/    
    @RequestMapping(value="/editstudent/{id}")    
    public String edit(@PathVariable int id, Model m){    
        StudentBean emp=dao.getStudentById(id);    
        m.addAttribute("command",emp);  
        return "studenteditform";    
    }    
    
    /* It updates model object. */    
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("studentBean") StudentBean studentBean){    
        dao.update(studentBean);    
        return "redirect:/viewstudent";    
    }  
    
    /* It deletes record for the given id in URL and redirects to /viewemp */    
    @RequestMapping(value="/deletestudent/{id}",method = RequestMethod.GET)    
    public String delete(@PathVariable int id){    
        dao.delete(id);    
        return "redirect:/viewstudent";    
    }     
	
}
