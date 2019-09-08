import java.io.PrintWriter;

public class ServerProtocol {

	public String processRequest(String input) {

		String l[];
		if(input.startsWith("READ"))
		{
			l = ServerSideMain.ad.getLine_READ(input.substring(6,input.length()-1));
			//l now looks like: l["XY4352","Arrival","12:40"]
			if(l==null)
				return "RERR "+input;
			else
				return "ROK <"+l[0]+"> <"+l[1]+"> <"+l[2]+">";
		}
		else //if(input.startsWith("WRITE"))
		{
			String[] s = input.split(" ");
			s[1]=s[1].substring(1,s[1].length()-1);
			s[2]=s[2].substring(1,s[2].length()-1);
			s[3]=s[3].substring(1,s[3].length()-1);

			return ServerSideMain.ad.addLine_WRITE(s[1], s[2], s[3])+" "+input;
		} 
	}
}

