package com.ispan.springbootdemo.model;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional   // 做完之後，把資料刪除(rollback)
public class WorkMessagesTest {

	@Autowired
	private WorkMessagesRepository workMessageDao;
	
	@Test
	public void testSave() {
		
		WorkMessages msg = new WorkMessages();
		msg.setText("new Message");
		
		workMessageDao.save(msg);
		
		Assertions.assertNotNull(msg.getId(), "NO ID generate");
		Assertions.assertNotNull(msg.getAdded(), "NO Date generate");
		
		WorkMessages retrived = workMessageDao.findById(msg.getId()).get();
		
		Assertions.assertEquals(msg, retrived,"Match Fail!!");
		
		
	}
}
