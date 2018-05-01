package com.tyrantqiao.testController;

import com.tyrantqiao.controller.UserController;
import com.tyrantqiao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * author: TyrantQiao
 * date: 2018/4/21
 * github: github.com/tyrantqiao
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class TestRedis {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

//	@Autowired
//	private RedisUserService<Long, User> redisTemplateService;

	@Test
	public void testUserController() throws Exception {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10; i++) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						try {
							mockMvc.perform(get("/user/get").param("id", "1")).andDo(print());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}


}
