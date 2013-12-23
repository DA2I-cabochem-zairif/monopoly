public class Alaligne
{

	public static void main(String[] args)
	{
		String nom = "";
    	String[] seq = nom.split(" ");
    	nom = "<html>";
    	int taille = 0;
    	for (String s : seq)
    	{
    		taille += s.length();
    		if (taille > 15)
    		{
    			nom += "<br>"+s;
    			taille = s.length();
    		}
    		else
    		{
    			nom += " "+s;
    		}
    	}
    	nom += "</html>";
	}
}