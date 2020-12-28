package nori.soft.game.sample.janken;

//じゃんけんゲーム ~CUI Version~
public class Main {
    public static void main(String[] args){
        Janken jankenGame=new Janken();
        
        jankenGame.setPlayerName();
        jankenGame.PrintRule();
        try{
           jankenGame.StartGame();
        }catch(InterruptedException iE){
            iE.printStackTrace();
            System.out.println("[!]ゲームを開始できませんでした");
        }
    }
}