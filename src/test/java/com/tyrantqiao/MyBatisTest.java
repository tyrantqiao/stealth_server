package com.tyrantqiao;

import com.tyrantqiao.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * date: 2018/4/24
 * {@code @autoConfigureTestDatabase} set test datasource, NONE mean do not replace the default database
 *
 * @author tyrantqiao
 * @version 0.0.1
 * Description: Test app true
 * blog https://tyrantqiao.github.io/Blog
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyBatisTest {
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * {@code @Rollback}是给测试方法标注的，当这一个测试方法是不需要留下痕迹时，就加入这个注解，当执行完方法后开始回滚。
	 * 与这个有点相似的是在controller类为方法标注{@code @Transaction(RollbackFor)}这个是若发生错误，则输出这个exception.class
	 *
	 * @throws Exception
	 */
	@Test
//	@Rollback
	public void deleteById() throws Exception {
//		orderMapper.insertOrder(new Order(1L, 123L));
		orderMapper.deleteByOrderId(1L);
	}
}
