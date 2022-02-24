package com.spring.mvc.user.repository;

import java.util.List;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {
	void register(UserVO user);
	
	Integer checkId(String account);
	
	void delete(String account);
	
	UserVO selectOne(String account);
	
	List<UserVO> selectAll();
	
}
/*
		$("#signIn-btn").click(function() {
		if (chk1 && chk2) {
			const id = $("#signInId").val();
			const pw = $("#signInPw").val();
			
			console.log("id : " + id);
			console.log("pw : " + pw);
			
			const user = {
					account : id,
					password : pw
			};
			
			$.ajax({
				type : "POST",
				url : "/user/loginCheck",
				headers : {
					"Content-Type" : "application/json"	
				},
				dataType : "text",
				data : JSON.stringify(user),
				success : function(result) {
					console.log("result : " + result);
					
					
					if (result === "idFail") {
					} else if (result === "pwFail") {
						console.log("id : " + id);
						console.log("pw : " + pw);
	
					} else {
						console.log("id : " + id);
						console.log("pw : " + pw);
						///location.href = "/";					
					}
					
				},
				error : function() {
					console.log("통신 실패! 으이구!");
				}
			});
		} else {
			alert("입력정보를 다시 확인하세요.");
		}
		
	});
	$.ajax({
				type : "POST",
				url : "/user/loginCheck",
				headers : {
					"Content-Type" : "application/json"	
				},
				dataType : "text",
				data : JSON.stringify(user),
				success : function(result) {
					console.log("result : " + result);
					
	
				},
				error : function() {
					console.log("통신 실패! 으이구!");
				}
			});
		} else {
			alert("입력정보를 다시 확인하세요.");
		}
		
		
	
*/