package com.task.siemens;

import com.task.siemens.model.AnalogData;
import com.task.siemens.model.DigitalData;
import com.task.siemens.service.AnalogDigitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SiemensTaskApplicationTests {
	@Autowired
	AnalogDigitalService adService;

	@Test
	void testUpdateDigital() {
		DigitalData d=new DigitalData(true);
		adService.updateDigital(0,d);
		assertEquals(adService.getDigital(0),true);
	}

	@Test
	void testUpdateAnalog() {
		AnalogData a=new AnalogData((float) 22.2);
		adService.updateAnalog(0,a);
		assertEquals(adService.getAnalog(0),(float)22.2);
	}

}
