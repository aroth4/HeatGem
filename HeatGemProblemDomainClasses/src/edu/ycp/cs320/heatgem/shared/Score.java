package edu.ycp.cs320.heatgem.shared;



public class Score {
//Score = (gravity*(sqrt(1000)/GameTimer))*health
//Crit = rand(NextInt(10)); 10=crit damage; else if >1=damage; else miss.
	
	private int PlayerScore;
	private int GameTimer;
	private int Health;

	
	public double getScore(int timer, Player player1)
	{
		GameTimer = timer;
		Health = player1.getHealth();
		PlayerScore = (int) ((9.81*(Math.sqrt(1000) * (Health + 1)) * 100 ) / GameTimer);
		
		return PlayerScore;
	}
	
}
