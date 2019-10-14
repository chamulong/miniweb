package com.jcj.miniweb.repository;

import com.jcj.miniweb.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author： 江成军
 * @Date：Create on  2019/7/18 23:00
 * @Description：数据仓库类：公司
 */
@Repository
public interface CompanyRepo extends JpaRepository<Company,Long>, JpaSpecificationExecutor
{
	//原生sql语句查询
	@Query(value="select * from company where cname=?1",nativeQuery = true)
	List<Company> findByNativeSQL(String companyname);

	//原生sql语句操作（涉及到数据变动的，如删除和更新，必须加注解@Modifying）
	@Modifying
	@Query(value = "update company set caddress =?1 where cname=?2",nativeQuery = true)
	void updateByName(String companyaddress,String companyname);

 //通过解析方法名创建查询
	Company findByCname(String companyname);

	//通过解析方法名创建查询
	Company findByCnameAndLegalpersonname(String companyname,String legalpersonname);

	//原生sql语句删除记录
	@Modifying
	@Query(value = "delete from company where uuid=?1",nativeQuery = true)
	void deleteByUuid(String uuid);
}
