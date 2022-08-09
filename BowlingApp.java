import java.util.*;
import java.io.*;
public class BowlingApp
{
    public static void main(String[] args)
    {
        Scanner inputNum =  new Scanner(System.in);
        Scanner inputText = new Scanner(System.in);
        
        System.out.println("--------------------------");
        System.out.println("-------Bowling Game-------");
        System.out.println("--------------------------");
        
        //prompt user to enter number of players
        System.out.print("Enter number of players: ");
        int playerNum = inputNum.nextInt();
        
        ArrayList <Player> playerList = new ArrayList<>();
        Player player;
        
        //input player names and insert into ArrayList
        for (int i=0; i<playerNum; i++)
        {
            System.out.print("Enter Player "+(i+1)+"'s name: ");
            String name = inputText.nextLine();
            player = new Player(name);
            playerList.add(player);
            System.out.println();
        }
        
        System.out.println();
        
        int throw1 = 0;
        int throw2 = 0;
        int score = 0;
        int frameScore = 0;
        int oriScore = 0;
        int oriScore2 = 0;
        int prevScore = 0;
        int extraThrow = 0;
        
        //loop for 1st to 9th frame
        for(int i=0; i<9; i++)
        {
            //loop for ArrayList index
            for(int j=0; j<playerList.size(); j++)
            {
                //reinitialize variables
                throw1 = 0;
                throw2 = 0;
                player = playerList.get(j);
                
                //input 1st Throw
                System.out.print(player.getName()+"'s turn:F"+(i+1)+" T1: ");
                throw1 = inputNum.nextInt();
                player.setThrow1(i, throw1);
                
                //prompt input for 2nd Throw
                if(player.getThrow1(i) < 10)
                {
                    System.out.print(player.getName()+"'s turn:F"+(i+1)+" T2: ");
                    throw2 = inputNum.nextInt();
                    player.setThrow2(i, throw2);
                    
                    //setSpare true when 1st & 2nd Throw = 10
                    if((player.getThrow1(i) + player.getThrow2(i)) == 10)
                    {
                        System.out.println("Nice one, a Spare!");
                        player.setSpare(i, true);
                    }
                }
                //setStrike true when 1st Throw = 10
                else if (player.getThrow1(i) == 10)
                {
                    System.out.println("Fantastic Strike!");
                    player.setStrike(i, true);
                }
                //input value is invalid
                else
                {
                    System.out.println("Invalid Input!");
                    System.exit(0);
                }
                
                //1st frame scoring
                if(i==0)
                {
                    frameScore = throw1 + throw2;
                }
                
                //2nd frame scoring
                else if(i==1)
                {
                    score = throw1 + throw2;
                    oriScore = player.getFrameScore(i-1);
                    
                    //1st frame is a strike
                    if(player.getStrike(i-1) == true)
                    {
                        player.setFrameScore((i-1), (oriScore + score));
                    }
                    //1st frame is a spare
                    else if(player.getSpare(i-1) == true)
                    {
                        player.setFrameScore((i-1), (oriScore + throw1));
                    }
                    
                    //frameScore is accumulated score
                    frameScore = score + player.getFrameScore(i-1);
                }
                
                else if(i>1 && i<9)
                {
                    score = throw1 + throw2;
                    oriScore = player.getFrameScore(i-1);
                    oriScore2 = player.getFrameScore(i-2);
                    
                    //check strike bonus score (case for consecutive strike)
                    if((player.getStrike(i-2) == true) && (player.getThrow2(i-1)==0))
                    {
                        player.setFrameScore((i-2), (oriScore2 + throw1));
                        oriScore = player.getThrow1(i-1) + player.getThrow2(i-1);
                        player.setFrameScore((i-1), (oriScore + player.getFrameScore(i-2)));
                    }
                    
                    //check bonus score
                    if(player.getStrike(i-1) == true)
                    {
                        prevScore = player.getFrameScore(i-1);
                        player.setFrameScore((i-1), (prevScore + score));
                    }
                    else if(player.getSpare(i-1) == true)
                    {
                        prevScore = player.getFrameScore(i-1);
                        player.setFrameScore((i-1), (prevScore + throw1));
                    }
                    
                    frameScore = score + player.getFrameScore(i-1);
                }
                player.setFrameScore(i, frameScore);
                
                //output scoring sheet
                System.out.println();
                System.out.println("Name\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
                for(int k=0; k<playerList.size(); k++)
                {
                    System.out.println(playerList.get(k).scoringSheet());
                }
                System.out.println();
            }
        }
        
        
        //loop for 10th frame
        for(int i=9; i<10; i++)
        {
            for(int j=0; j<playerList.size(); j++)
            {
                //reinitialize variables
                throw1 = 0;
                throw2 = 0;
                extraThrow = 0;
                player = playerList.get(j);
                
                //input 1st Throw
                System.out.print(player.getName()+"'s turn:F"+(i+1)+" T1: ");
                throw1 = inputNum.nextInt();
                player.setThrow1(i, throw1);
                
                //prompt input 2nd Throw
                if(player.getThrow1(i) < 10)
                {
                    System.out.print(player.getName()+"'s turn:F"+(i+1)+" T2: ");
                    throw2 = inputNum.nextInt();
                    player.setThrow2(i, throw2);
                    
                    //input extraThrow when spare
                    if((player.getThrow1(i) + player.getThrow2(i)) == 10)
                    {
                        System.out.println("Nice one, a Spare!");
                        player.setSpare(i, true);
                        System.out.print(player.getName()+"'s turn:F"+(i+1)+" T3: ");
                        extraThrow = inputNum.nextInt();
                        if(extraThrow == 10)
                        {
                            System.out.println("Fantastic Strike!");
                        }
                    }
                    else
                    {
                        extraThrow = 0;
                    }
                }
                
                //input 2nd and extraThrow when strike
                else if (player.getThrow1(i) == 10)
                {
                    System.out.println("Fantastic Strike!");
                    player.setStrike(i, true);
                    
                    System.out.print(player.getName()+"'s turn:F"+(i+1)+" T2: ");
                    throw2 = inputNum.nextInt();
                    player.setThrow2(i, throw2);
                    if(player.getThrow2(i) == 10)
                    {
                        System.out.println("Fantastic Strike!");
                    }
                    
                    System.out.print(player.getName()+"'s turn:F"+(i+1)+" T3: ");
                    extraThrow = inputNum.nextInt();
                    if(extraThrow == 10)
                    {
                        System.out.println("Fantastic Strike!");
                    }
                    else if((player.getThrow2(i) + extraThrow) == 10)
                    {
                        System.out.println("Nice one, a Spare!");
                    }
                }
                else
                {
                    System.out.println("Invalid Input!");
                    System.exit(0);
                }
                
                player.setExtraThrow(extraThrow);
                score = throw1 + throw2 + extraThrow;
                
                oriScore = player.getFrameScore(i-1);
                oriScore2 = player.getFrameScore(i-2);
                    
                //check strike bonus score (case for consecutive strike)
                if((player.getStrike(i-2) == true) && (player.getThrow2(i-1)==0))
                {
                    player.setFrameScore((i-2), (oriScore2 + throw1));
                    oriScore = player.getThrow1(i-1) + player.getThrow2(i-1);
                    player.setFrameScore((i-1), (oriScore + player.getFrameScore(i-2)));
                }
                    
                //check bonus score
                if(player.getStrike(i-1) == true)
                {
                    prevScore = player.getFrameScore(i-1);
                    player.setFrameScore((i-1), (prevScore + throw1 + throw2));
                }
                else if(player.getSpare(i-1) == true)
                {
                    prevScore = player.getFrameScore(i-1);
                    player.setFrameScore((i-1), (prevScore + throw1));
                }
                frameScore = score + player.getFrameScore(i-1);
                player.setFrameScore(i, frameScore);
                
                
                System.out.println();
                System.out.println("Name\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
                for(int k=0; k<playerList.size(); k++)
                {
                    System.out.println(playerList.get(k).scoringSheet());
                }
                System.out.println();
            }
        }
    }
}
