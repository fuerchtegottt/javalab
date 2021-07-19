package de.g3ll3rt.yakyuuken;

public class GameController {
    private int stage;
    private char playerHand;
    private char opponentHand;
    private boolean gameRunning;
    private static GameController instance;

    public static final char HANDTYPE_PAPER    = 'P';
    public static final char HANDTYPE_ROCK     = 'R';
    public static final char HANDTYPE_SCISSORS = 'S';

    public static final char RESULT_WIN        = 'W';
    public static final char RESULT_LOOSE      = 'L';
    public static final char RESULT_DRAW       = 'D';

    public static GameController getInstance() {
        if( instance == null){
            instance = new GameController();
        }

        return instance;
    }

    private GameController(){

    }

    public void placeHand(char playerHand){
        this.playerHand = playerHand;
    }

    public char getOpponentHand(){
        return opponentHand;
    }

    public char getPlayerHand(){
        return playerHand;
    }

    public int getStage(){
        return stage;
    }

    public boolean isGameRunning(){
        return gameRunning;
    }

    private char computeOpponentHand(){
        int rnd = 1 + (int)(Math.random() * ((3 - 1) + 1));
        switch (rnd){
            case 1:
                return HANDTYPE_PAPER;
            case 2:
                return HANDTYPE_ROCK;
            case 3:
                return HANDTYPE_SCISSORS;
            default:
                return HANDTYPE_PAPER;
        }
    }

    public void resetGame() {
        stage = 1;
        playerHand = HANDTYPE_PAPER;
        gameRunning = true;
    }

    public char getResult(){
        opponentHand = computeOpponentHand();
        if ( opponentHand == playerHand ) {
          return RESULT_DRAW;
        } else if(
                ( opponentHand == GameController.HANDTYPE_PAPER    && playerHand == GameController.HANDTYPE_SCISSORS )
        ||      ( opponentHand == GameController.HANDTYPE_ROCK     && playerHand == GameController.HANDTYPE_PAPER    )
        ||      ( opponentHand == GameController.HANDTYPE_SCISSORS && playerHand == GameController.HANDTYPE_ROCK     )
        ){
            stage = stage + 1;
            if (stage > 3) {
                gameRunning = false;
            }
            return RESULT_WIN;
        } else{
            gameRunning = false;
            stage = 1;
            return RESULT_LOOSE;
        }
    }
}