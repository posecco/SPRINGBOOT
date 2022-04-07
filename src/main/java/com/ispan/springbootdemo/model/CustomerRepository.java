package com.ispan.springbootdemo.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("from Customer where name= :name")
	public List<Customer> findCustomerByName(@Param(value="name")String name);
	
	@Query(value="select * from Customer where name = :name", nativeQuery = true)
	public List<Customer> findCustomerByName2(@Param("name") String name);
	
	@Transactional //因為這邊沒有寫service，沒有在service開交易，所以要在這邊編寫
	@Modifying  //要對資料庫進行修改或刪除時，必須使用這個標籤
	@Query(value="delete from customer where id =?1", nativeQuery = true) //nativeQuery = true，才能使用原生sql語法
	public void deleteCustomerById(Integer id);
	
	public List<Customer> findByLevelOrderByName(Integer level);
}
