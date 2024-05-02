package com.tom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jedis")
public class JedisController {

	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("save")
	public void saveStudent() {
		
		Student student = new Student("s002", "John Doe", Student.Gender.MALE, 1);
		student.setExpired(60L);
		
		studentRepository.save(student);
	}
	
	@GetMapping("get")
	public void getStudent() {
		Student retrievedStudent = 
				  studentRepository.findById("Eng2015001").get();
		
		System.out.println("Get:" + retrievedStudent);
	}
	
	@PostMapping("addUser")
	public String addUser(@RequestBody UserInfoRequest body) {
		/**
		 * 

		private String userIp;                              // 使用者IP
	    private String cifId;                               // 人員ID
	    private String userId;                              // 使用者代碼
	    private String userName;                            // 使用者姓名
	    private String agencyName;                          // 機關名稱
	    private String branchName;                          // 分支名稱
	    private Long agencyId;                              // 機關ID
	    private Long branchId;                              // 分支ID
	    private String agencyCodingId;                      // 繳納機關代碼
	    private String branchCodingId;                      // 總分支代碼
	    private String applicationId;                       // application ID
	    private List employeeRoles;                         // 身分別
		 */
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserIp("127.0.0.1");
		userInfo.setCifId("CIF0000001");
		userInfo.setUserId("user01");
		userInfo.setUserName("tom");
		userInfo.setAgencyName("");
		
		return "";
	}
	
}
