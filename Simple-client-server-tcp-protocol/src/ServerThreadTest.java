import static org.junit.Assert.*;

import org.junit.Test;

public class ServerThreadTest {

	@Test
	public void test() {
		
		//{"XY4352","Arrival","12:40"});
		String original = "WRITE <DB9761> <Arrival> <12:40>";
		String[] s = original.split(" ");
		s[1]=s[1].substring(1,original.length()-1);
		s[2]=s[2].substring(1,original.length()-1);
		s[3]=s[3].substring(1,original.length()-1);

		//String code = original.substring(7,original.length()-1);
		

	}

}
