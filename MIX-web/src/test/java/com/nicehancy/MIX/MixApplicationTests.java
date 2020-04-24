package com.nicehancy.MIX;

import com.nicehancy.MIX.base.BaseSpringTest;
import com.nicehancy.MIX.common.utils.FileIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Slf4j
class MixApplicationTests extends BaseSpringTest {

	@Test
	void createFileIdTest(){
		String fileId = FileIdUtil.createFileId();
		log.info("创建的文件ID：{}", fileId);
	}

	@Test
	void dateTest(){

		Date t1 = new Date();
		Date t2 = new Date();

		log.info("{}", t1.equals(t2));
	}

	@Test
	void integerTest(){

		int i = 1;
		Integer integer = Integer.valueOf(i);
		int j = integer.intValue();
		log.info("{}", integer);
		log.info("{}", j);
	}

	@Test
	void equalsTest(){
		int a = 10;
		double b = 10.0;
		long c = 10L;
		log.info("{}", b == c);
	}

	@Test
	void listTest(){
		List a = new ArrayList(2);
		a.add(1);
		a.add(2);
		a.add(3);
		log.info("数组大小：{}， 数组详情:{}", a.size(),a);
	}

	@Test
	void vectorTest(){
		List v = new Vector();
		v.add(1);
		v.add(2);
		log.info("vector数组大小:{}, v:{}", v.size(), v);
	}
}
