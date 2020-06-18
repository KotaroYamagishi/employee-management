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

import com.domain.Development;

@Repository
@Component
public class DevelopmentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	Row_Mapperクラスの作成
	public static final RowMapper<Development> DEVELOPMENT_ROW_MAPPER
		=(rs,i)->{
			Development development =new Development();
			development.setDevelopmentId(rs.getInt("development_id"));
			development.setDevelopmentName(rs.getString("development_name"));
			return development;
		};
	
	
//	id検索
	public Development load(Integer id) {
		String sql="SELECT * FROM developments WHERE id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);
		Development development=template.queryForObject(sql, param, DEVELOPMENT_ROW_MAPPER);
		
		System.out.println("Repositoryのload()が呼ばれました");
		return development;
	}
	
//	全検索
	public List<Development> findAll(){
		String sql="SELECT * FROM developments";
		List<Development> depList=template.query(sql, DEVELOPMENT_ROW_MAPPER);
		
		System.out.println("RepositoryのfindAll()が呼ばれました");
		return depList;
	}
	
	
//	保存（新規は登録、既存は更新）
	public void save(Development development) {
		if(development.getDevelopmentId()==null) {
			String insertSql="INSERT INTO developments (development_name) values (:developmentName)";
			SqlParameterSource param=new BeanPropertySqlParameterSource(development);
			template.update(insertSql, param);
		}else {
			String updateSql="UPDATE developments SET development_name=:developmentName WHERE development_id=:developmentId";
			SqlParameterSource param=new BeanPropertySqlParameterSource(development);
			template.update(updateSql, param);
		}
		
		System.out.println("Repositoryのsave()が呼ばれました");	
	}
	
	
//	行削除
	public void deleteById(Integer id) {
		String sql="DELETE FROM developments WHERE development_id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
		
		System.out.println("RepositoryのdeleteById()が呼ばれました");
	}
	
//	無署名を曖昧検索
	public List<Development> findByName(String name){
		String sql="SELECT * FROM developments WHERE development_name LIKE '%:name%'";
		SqlParameterSource param=new MapSqlParameterSource().addValue("name", name);
		List<Development> depList=(List<Development>) template.queryForObject(sql, param, DEVELOPMENT_ROW_MAPPER);
		return depList;
	}
}
