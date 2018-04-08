package com.tyrantQiao.stealth.service;

import com.tyrantQiao.stealth.POJO.Result;
import com.tyrantQiao.stealth.POJO.User;
import com.tyrantQiao.stealth.enums.ResultEnum;
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
}
