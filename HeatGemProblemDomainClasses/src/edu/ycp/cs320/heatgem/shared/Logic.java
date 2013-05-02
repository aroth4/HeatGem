package edu.ycp.cs320.heatgem.shared;

import java.util.Random;

public class Logic {
	 public static void doBattle(Player Player1, Player Player2)  //Battle
     {
         //Player Monster = new Player("Monster");
         //Battle skirmish = new Battle(Player1, Monster);
		 Battle skirmish = new Battle(Player1, Player2);
//         while (true)
//         {
        	 Random TRandom = new Random();
     		 int MTurn = (int)TRandom.nextInt(2);

             skirmish.Attack(); //Player attacks monster
             if(MTurn ==1){  //Monster attacks player OR
     			 skirmish.MonsterAttack();
     		 }
     		 else{
     			skirmish.MonsterHeal(); //Monster Heals
     		 }
             Player1.setHealth(skirmish.getHealth1()); //Set new health values
             Player2.setHealth(skirmish.getHealth2());
//             if (skirmish.battleState() != 0)
//                 break;
         //}

     }
	 
	 // maybe eliminate second parameter
	 public static void doHeal(Player Player1, Player Player2)  //Heal
     {
         Player Monster = new Player("Monster");
         Battle Heal = new Battle(Player1, Monster);
//         while (true)
//         {
        	 Random TRandom = new Random();
     		 int MTurn = (int)TRandom.nextInt(2);


             Heal.heal(); //Player Heals
     		 if(MTurn ==1){ //Monster Heals
     			 Heal.MonsterHeal(); 
     		 }
     		 else{
     			 Heal.MonsterAttack(); //Monster Attacks
     		 }
             Player1.setHealth(Heal.getHealth1()); //Set new health
             Player2.setHealth(Heal.getHealth2());
//             if (Heal.battleState() != 0)
//                 break;
//         }
//
     }
}
