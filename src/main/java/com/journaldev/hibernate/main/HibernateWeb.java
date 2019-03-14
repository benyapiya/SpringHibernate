package com.journaldev.hibernate.main;

import java.util.Date;

import org.hibernate.Session;

import com.journaldev.hibernate.model.Employee;
import com.journaldev.hibernate.util.HibernateUtil;
import com.journaldev.hibernate.main.EmployeeForm;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.hibernate.Query;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@EnableAutoConfiguration
public class HibernateWeb {

		private String username;
		private String hobby;
    @PostMapping("/add_entry")
    public String add_entry(@RequestBody EmployeeForm employeeForm) {

			username = employeeForm.getUsername();
			hobby = employeeForm.getHobby();
  		Employee emp = new Employee();
			emp.setName(username);
			emp.setRole(hobby);
			emp.setInsertTime(new Date());
			String db_records="";
			//Get Session

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

				try {
					//start transaction
					session.beginTransaction();
					//Save the Model object
					session.save(emp);
					//Commit transaction
					session.getTransaction().commit();
					System.out.println("Employee ID="+emp.getId());

					session = HibernateUtil.getSessionFactory().getCurrentSession();

					session.beginTransaction();
					Query query = session.createQuery("from Employee");
					List<Employee> list = query.list();

					for (int i = 0; i < list.size(); i++) {
						Employee e = (Employee) list.get(i);
					  db_records=db_records+" :: "+e.getName()+", "+e.getRole();
					}
				} catch (Exception e){
					System.out.println("Exception caught in Catch block");
					System.out.println(e.toString());
					HibernateUtil.closeSessionFactory();
				}

			return "{\"all_entries\":\"" +db_records+"\"}";
    }

	public static void main(String[] args) {
		SpringApplication.run(HibernateWeb.class, args);
	}

}
