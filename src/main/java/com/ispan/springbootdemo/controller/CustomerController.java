package com.ispan.springbootdemo.controller;

//這邊在測試用POSTMAN模擬資料傳輸
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.springbootdemo.model.Customer;
import com.ispan.springbootdemo.model.CustomerRepository;

@RestController //讓這個Controller所有方法都回傳JSON字串
@Controller //宣告為Controller
public class CustomerController {

	@Autowired //讓他去找CustomerRepository這個dao
	private CustomerRepository dao;
	
//	@ResponseBody //輸出JSON格式的資料
	@PostMapping(value = "customer/insert") //宣告進入的路徑是customer/insert，用Post方法進來
	public Customer insertCustomer() { //這是寫入的方法
		Customer cus = new Customer("Will", 5); //給值/寫死第1個欄位名字是Will、第2個欄位等級是5
		Customer resCus = dao.save(cus); //呼叫dao過來給值，寫進資料庫
		
		return resCus; //再將接到值的物件回傳給dao
	}
	
	@ResponseBody
	@PostMapping(value = "customer/insert2") //宣告進入的路徑是customer/insert2，用Post方法進來
	public Customer insertCustomer(@RequestBody Customer cus) { //
		Customer resCus = dao.save(cus);//透過dao存取
		return resCus;//回傳給進來的人
	}
	
//	@ResponseBody
	@PostMapping(value = "customer/insertAll") //宣告進入的路徑是customer/insertAll，用Post方法進來
	public List<Customer> insertCustomer(@RequestBody List<Customer> cus) { //多筆資料用泛型做傳輸
		List<Customer> responseList = dao.saveAll(cus); //存取多筆資料的方法
		return responseList;
	}
	
	//實作JPARepository物件功能
	@GetMapping(value="customer/get/{id}") //宣告進入的路徑是customer/get/{id}，用Get方法進來
	public Customer getCustomerById(@PathVariable Integer id) { //@PathVariable抓取指定路徑變數
		Optional<Customer> responseCus = dao.findById(id);//Optional物件替代null去判斷有沒有拿到東西，因為null有太多可能難以判別
		
		if(responseCus.isPresent()) {//Optional物件的isPresent方法可以判斷有沒有拿到東西
			return responseCus.get();//get方法可以拿到該物件，然後回傳
		}
		
		return null;//如果沒拿到東西回傳null
	}
	
	@GetMapping(value = "customer/get") //宣告進入的路徑是customer/get，用Get方法進來
	public Customer getCustomerById2(@RequestParam Integer id) {//接form:form表單值的方式透過指定key值接進來，這邊是id
		Optional<Customer> responseCus = dao.findById(id);//Optional物件替代null去判斷有沒有拿到東西，因為null有太多可能難以判別

		if (responseCus.isPresent()) {//Optional物件的isPresent方法可以判斷有沒有拿到東西
			return responseCus.get();//get方法可以拿到該物件，然後回傳
		}

		return null;//如果沒拿到東西回傳null
	}
	//這邊在測試當我們可以拿到所有資料時，想要把資料做分頁
	@GetMapping(value="customer/page/{pageNumber}") //宣告進入的路徑是customer/page/{pageNumber}，用Get方法進來
	public List<Customer> findByPage(@PathVariable Integer pageNumber){//@PathVariable抓取指定路徑變數
		Pageable pgb = PageRequest.of(pageNumber-1, 3,Sort.Direction.DESC,"id");
		Page<Customer> page = dao.findAll(pgb);
		List<Customer> list = page.getContent();
		return list;
	}
	//實作HQL語法的方式
	@GetMapping(value="customer/findByName") //宣告進入的路徑是customer/findByName，用Get方法進來
	public List<Customer> findByName(@RequestParam String name){//接form:form表單值的方式透過指定key值接進來，這邊是name
		return dao.findCustomerByName(name);
	}
	//實作原生SQL語法的方式
	@GetMapping(value="customer/findByName2") //宣告進入的路徑是customer/findByName2，用Get方法進來
	public List<Customer> findByName2(@RequestParam String name){//接form:form表單值的方式透過指定key值接進來，這邊是name
		return dao.findCustomerByName2(name);
	}
	//實作在頁面進行刪除
	@GetMapping(value="customer/delete/{id}") //宣告進入的路徑是customer/delete/{id}，用Get方法進來
	public boolean deleteCustomer(@PathVariable Integer id) {//@PathVariable抓取指定路徑變數
		dao.deleteCustomerById(id);//透過id進行刪除，把畫面指定的id接進來
		return true;//回傳結果
	}
	
	@GetMapping(value="customer/level/{level}") //宣告進入的路徑是customer/level/{level}，用Get方法進來
	public List<Customer> findByLevel(@PathVariable Integer level){ //因為有可能是多筆資料，因此回傳值是List<Customer>
		return dao.findByLevelOrderByName(level); //排列順序是英文先，再中英混雜，最後是中文
	}
}
