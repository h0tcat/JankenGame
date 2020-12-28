package nori.soft.game.sample.janken;

import java.util.Scanner;

import nori.soft.game.sample.janken.user.Cpu;
import nori.soft.game.sample.janken.user.Player;
import nori.soft.game.sample.janken.user.You;
import nori.soft.game.sample.janken.user.Player.Hand;

import java.util.Random;

public class Janken {

    private String playerName;
    private final String computerName = "CPU";
    private Player[] players;

    private Scanner inputScanner;

    private boolean isYouWinCpuLose;

    private boolean isCpuWinYouLose;

    private boolean isDraw;

    public Janken() {
        this.players = new Player[2];
        this.players[0] = new You();
        this.players[1] = new Cpu();
        this.players[0].hand=Hand.NONE;
        this.players[1].hand=Hand.NONE;

        this.inputScanner = new java.util.Scanner(System.in);
        System.out.print("あなたの名前を入力してください >");
    }

    // Scannerでプレイ時に使用するユーザー名を入力してもらう
    public void setPlayerName() {
        this.playerName = this.inputScanner.nextLine();
        this.players[1].setName(this.computerName);
        this.players[0].setName(this.playerName);
        System.out.printf("\n[+]じゃんけんゲームへようこそ! %sさん!\n", this.playerName);
    }

    public void PrintRule() {
        System.out.println("\n[ルール説明]\n");
        System.out.println("グーチョキパーを0~2の数字で選択できます。\nqキーを押すとゲームを終了できます。\n");
        System.out.println("準備ができたら、Enterキーを押してください");

        this.inputScanner.nextLine();
    }

    private String userCommand;

    private String[] handCommandList = { "0. グー", "1. チョキ", "2. パー" };

    // ユーザーが操作可能なコマンドを表示する
    private void PrintHandCommand() {
        for (String handCommand : handCommandList) {
            System.out.printf("%s\n", handCommand);
        }
    }

    private void PrintHandResult() {
        for (Player player : this.players) {
            System.out.printf("%sの出した手: %s\n\n", player.getName(), player.getHandStatus());
        }
        this.JudgeWinorLose();
    }

    private boolean JudgeWinorLose() {
        this.isYouWinCpuLose = (players[0].hand == Hand.ROCK && players[1].hand == Hand.SCISSORS)
                || (players[0].hand == Hand.SCISSORS && players[1].hand == Hand.PAPER)
                || (players[0].hand == Hand.PAPER && players[1].hand == Hand.ROCK);
        this.isCpuWinYouLose=!this.isYouWinCpuLose;
        this.isDraw = players[0].hand.equals(players[1].hand);

        if (this.isYouWinCpuLose) {
            System.out.println("あなたの勝ち!");
            this.players[0].winCount++;
            this.players[1].loseCount++;

            return this.isYouWinCpuLose;
        } else if (this.isCpuWinYouLose) {
            System.out.println("CPUの勝ち!");
            this.players[1].winCount++;
            this.players[0].loseCount++;

            return this.isCpuWinYouLose;
        } else {
            System.out.println("引き分け!");

            return this.isDraw;
        }
    }

    private void PrintWinLoseHistory() {

        System.out.println("--[勝敗の記録]--\n");
        for (Player player : this.players) {
            System.out.printf("%s : %d勝%d敗\n\n", player.name, player.winCount, player.loseCount);
        }
        System.out.println("---");
    }

    Random computerCommandRandom;

    public void StartGame() throws InterruptedException {
        System.out.println("[ゲームスタート!]");
        this.userCommand = "";
        int commandNumber = -1;
        this.computerCommandRandom = new Random();
        while (true) {

            this.PrintHandCommand();

            while (true) {
                System.out.print("番号でコマンドを入力してください >");
                this.userCommand = this.inputScanner.nextLine();
                try {
                    commandNumber = Integer.parseInt(this.userCommand);
                    if (commandNumber <0 || commandNumber > 2)
                        System.out.println("番号が範囲外です。");
                    else break;
                } catch (NumberFormatException NumberEx) {
                    System.out.println("コマンドを認識できません");
                }
                if (this.userCommand.equals("q")){
                    System.out.println("また遊んでね");
                    System.exit(0);
                }
            }

            this.players[0].execCommand(commandNumber);
            this.players[1].execCommand(this.computerCommandRandom.nextInt(3));
            System.out.println("ジャンケーン...");
            Thread.sleep(1000);
            System.out.println("ぽん!");
            // 間を取る
            Thread.sleep(500);

            this.PrintHandResult();
            Thread.sleep(250);
            this.PrintWinLoseHistory();
            commandNumber = -1;
        }
    }
}