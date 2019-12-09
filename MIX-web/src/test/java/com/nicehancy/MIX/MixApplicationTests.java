package com.nicehancy.MIX;

import com.nicehancy.MIX.common.utils.FileIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MixApplicationTests {

	@Test
	void createFileIdTest(){
		String fileId = FileIdUtil.createFileId();
		log.info("创建的文件ID：{}", fileId);
	}
}
