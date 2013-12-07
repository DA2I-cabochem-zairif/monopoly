public class Calcul
{
	public static void main(String[] args)
	{
		int actuel = 1, dep = -3, arrive = 0, total = 40;
		
		if (actuel + dep < 1)
		{
			for (int i = actuel ; i > 0 ; i--)
			{
				dep++;
			}
			arrive = total + dep;
		}
		else if (actuel + dep > total)
		{
			for (int i = actuel ; i < total ; i++)
			{
				dep--;
			}
			arrive = dep;
		}
		else
		{
			arrive = actuel + dep;
		}
		
		System.out.println("Case cible : "+arrive);
	}
}