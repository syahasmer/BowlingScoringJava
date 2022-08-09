public class Player
{
    //attributes
    private String name;
    private int[] throw1 = new int [10];
    private int[] throw2 = new int [10];
    private boolean[] strike = new boolean [10];
    private boolean[] spare = new boolean [10];
    private int[] frameScore = new int [10];
    private int extraThrow;
    
    //default constructor
    public Player(String name){this.name = name;}
    
    //setter
    public void setThrow1(int[] throw1) {this.throw1 = throw1;}
    public void setThrow1(int i, int t1) {this.throw1[i] = t1;}
    public void setThrow2(int[] throw2) {this.throw2 = throw2;}
    public void setThrow2(int i, int t2) {this.throw2[i] = t2;}
    public void setStrike(boolean[] strike) {this.strike = strike;}
    public void setStrike(int i, boolean st) {this.strike[i] = st;}
    public void setSpare(boolean[] spare) {this.spare = spare;}
    public void setSpare(int i, boolean sp) {this.spare[i] = sp;}
    public void setFrameScore(int[] frameScore) {this.frameScore = frameScore;}
    public void setFrameScore(int i, int fS) {this.frameScore[i] = fS;}
    public void setExtraThrow(int extraThrow) {this.extraThrow = extraThrow;}

    //getter
    public String getName() {return this.name;}
    public int getThrow1(int i) {return this.throw1[i];}
    public int[] getThrow1() {return this.throw1;}
    public int getThrow2(int i) {return this.throw2[i];}
    public int[] getThrow2() {return this.throw2;}
    public boolean getStrike(int i) {return this.strike[i];}
    public boolean[] getStrike() {return this.strike;}
    public boolean getSpare(int i) {return this.spare[i];}
    public boolean[] getSpare() {return this.spare;}
    public int getFrameScore(int i) {return this.frameScore[i];}
    public int[] getFrameScore() {return this.frameScore;}
    public int getExtraThrow() {return this.extraThrow;}
    
    //method scoringSheet()
    public String scoringSheet()
    {
        String scoringSheet = null;
        scoringSheet = name+"\t";
        for(int i=0; i<10; i++)
        {
            String s1 = String.valueOf(throw1[i]);
            String s2 = String.valueOf(throw2[i]);
            
            if(throw1[i] == 10)
            {
                s1 = "X";
                s2 = " ";
            }
            else if((throw1[i] + throw2[i]) == 10)
            {
                s2 = "/";
            }
            else if(throw1[i] == 0)
            {
                s1 = "-";
            }
            else if(throw2[i] == 0)
            {
                s2 = "-";
            }
            
            if(i<9)
            {
                scoringSheet = scoringSheet +s1+ "|" +s2+"|"+frameScore[i]+"\t";
            }
            else
            {
                if(throw2[i] == 10)
                    s2 = "X";
                else if((throw1[i] + throw2[i]) == 10)
                    s2 = "/";
                else if(throw2[i] == 0)
                    s2 = "-";
                else 
                    s2 = String.valueOf(throw2[i]);
                
                String s3 = String.valueOf(extraThrow);
                if(extraThrow == 10)
                    s3 = "X";
                else if((throw2[i] + extraThrow) == 10)
                    s3 = "/";
                else if(extraThrow == 0)
                    s3 = "-";
                else
                    s3 = String.valueOf(extraThrow);
                
                scoringSheet = scoringSheet +s1+ "|" +s2+ "|" +s3+ "|" +frameScore[i]+"\t";
            }
        }
        return scoringSheet;
    }
}
