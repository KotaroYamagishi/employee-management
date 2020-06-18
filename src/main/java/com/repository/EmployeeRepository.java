package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.domain.Employee;

@Repository
@Component
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
	=(rs,i)->{
		Employee employee=new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setAge(rs.getInt("age"));
		employee.setGender(rs.getString("gender"));
		employee.setDevelopmentId(rs.getInt("development_id"));
		return employee;
	};
	
	
//	主キー検索
	public Employee load(Integer id) {
		String sql="SELECT id,name,age,gender,development_id FROM employees WHERE id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);
		Employee employee=template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		System.out.println("Repositoryのload()が呼ばれました");
		return employee;
	}
	
	
//	全件検索
	public List<Employee> findAll() {
		String sql="SELECT * FROM employees ORDER BY age";
		List<Employee> empList=template.query(sql, EMPLOYEE_ROW_MAPPER);
		System.out.println("RepositoryのfindAll()が呼ばれました");
		return empList;
	}
	
	
//	従業員情報の更新
	public void save(Employee employee) {
		SqlParameterSource param=new BeanPropertySqlParameterSource(employee);
		
		if(employee.getId()==null) {
			String insertSql="INSERT INTO employees(name,age,gender,development_id) "
					+ "values(:name,:age,:gender,:developmentId)";
			template.update(insertSql, param);
		}else {
			String updateSql="UPDATE employees SET name=:name,age=:age,gender=:gender,development_id=:developmentId WHERE id=:id";
			template.update(updateSql, param);
		}
		
		System.out.println("Repositoryのsave()が呼ばれました");
	}
	
	
//	主キーを使って一行削除
	public void deleteById(Integer id) {
		String deleteSql="DELETE FROM employees WHERE id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);
		System.out.println("RepositoryのdeleteById()が呼ばれました");
	}
}
