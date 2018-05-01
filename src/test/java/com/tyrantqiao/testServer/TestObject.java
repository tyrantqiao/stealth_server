package com.tyrantqiao.testServer;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * author: TyrantQiao
 * date: 2018/4/17
 * github: github.com/tyrantqiao
 */
public class TestObject {
	@Test
	public void testUnixTimeStamp(){
//		 Long unixTimeStamp=Instant.now().getEpochSecond();
//		 System.out.println(new Random().nextInt(10));
//		 System.out.println(unixTimeStamp.toString().substring(6));
		LocalDateTime localDateTime=LocalDateTime.now();
		System.out.println(localDateTime.getDayOfMonth());
	}
}
