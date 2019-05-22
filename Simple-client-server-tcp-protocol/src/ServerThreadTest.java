import static org.junit.Assert.*;

import org.junit.Test;

public class ServerThreadTest {

	@Test
	public void test() {
		
		//{"XY4352","Arrival","12:40"});
		String clientCommand = "WRITE <DB0061> <Arrival> <11:40>";
		String[] s = clientCommand.split(" ");
		s[1]=s[1].substring(1,s[1].length()-1);
		s[2]=s[2].substring(1,s[2].length()-1);
		s[3]=s[3].substring(1,s[3].length()-1);

		assertEquals("DB0061", s[1]);
		//String code = original.substring(7,original.length()-1);
		

	}

}
