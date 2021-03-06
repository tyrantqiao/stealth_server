package com.tyrantqiao.service;

import com.tyrantqiao.pojo.Result;
import com.tyrantqiao.entity.User;
import com.tyrantqiao.enums.ResultEnum;
import org.springframework.stereotype.Service;

@Service("resultService")
public class ResultService {
	public Result<User> success(User User) {
		Result<User> result = new Result<>();
		result.setStatus(200);
		result.setMessage("succeed");
		result.setData(User);
		return result;
	}

	public Result<User> success(User user,String msg){
		var result=new Result<User>();
		result.setData(user);
		result.setMessage(msg);
		result.setStatus(200);
		return result;
	}

	public Result<User> error(ResultEnum resultEnum) {
		var result=new Result<User>(resultEnum);
		result.setData(null);
		return result;
	}

	public Result<User> error(int status, String msg) {
		var result = new Result<User>();
		result.setStatus(status);
		result.setMessage(msg);
		result.setData(null);
		return result;
	}

	public Result<User> error(int status, String errorMessage, User user) {
		//TODO make the error can output user--pojo
		var result=new Result<User>();
		result.setData(user);
		result.setMessage(errorMessage);
		result.setStatus(status);
		return result;
	}
}
