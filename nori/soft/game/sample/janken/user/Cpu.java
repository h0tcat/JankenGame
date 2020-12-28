package nori.soft.game.sample.janken.user;

public class Cpu extends Player{

    @Override
    public void execCommand(int command) {
        switch(command){
            case Player.rock:
                super.hand=Hand.ROCK;
                super.handStatus="グー";
                break;
            case Player.scissors:
                super.hand=Hand.SCISSORS;
                super.handStatus="チョキ";
                break;
            case Player.paper:
                super.hand=Hand.PAPER;
                super.handStatus="パー";
                break;
        }
    }

    @Override
    public String getHandStatus(){
        return super.handStatus;
    }
    @Override
    public String getName(){
        return super.name;
    }
    @Override
    public void setName(String name){
        super.name=name;
    }
    
}
