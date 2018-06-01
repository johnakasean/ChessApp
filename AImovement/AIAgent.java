import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/
  public Move randomMove(Stack possibilities){
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }
  public Move nextBestMove(Stack whitePsb, Stack blackPsb) {
      Stack black = (Stack) blackPsb.clone();
      Stack white = (Stack) whitePsb.clone();
      Move whiteMove, normalMove, bestMove;
      double Score = 0.0;
      double selectedPiece = 0.0;
      Square blackPos;
      bestMove = null;
      while (!whitePsb.empty()) {
          whiteMove = (Move) whitePsb.pop();
          normalMove = whiteMove;
          if ((normalMove.getStart().getYC() < normalMove.getLanding().getYC())
                  && (normalMove.getLanding().getXC() == 3) && (normalMove.getLanding().getYC() == 3)//keeps ai moving pieces down
                  || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 3)
                  || (normalMove.getLanding().getXC() == 3) && (normalMove.getLanding().getYC() == 4)
                  || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 4)) {
              Score = 0.1;
              if (Score > selectedPiece) {//assigns score to get bestmove
                  selectedPiece = Score;
                  bestMove = normalMove;
              }
          }
          while (!black.isEmpty()) {
              Score = 0;
              blackPos = (Square) black.pop();
              if ((normalMove.getLanding().getXC() == blackPos.getXC()) &&
              (normalMove.getLanding().getYC() == blackPos.getYC())) {
                  if (blackPos.getName().equals("BlackPawn")) {//assigns scores to pieces
                      Score = 1;
                  }
                  else if (blackPos.getName().equals("BlackBishop") ||
                           blackPos.getName().equals("BlackKnight")) {
                      Score = 3;
                  }
                  else if (blackPos.getName().equals("BlackRook")) {
                      Score = 5;
                  }
                  else if (blackPos.getName().equals("BlackQueen")) {
                      Score = 9;
                  }
                  else if (blackPos.getName().equals("BlackKing")) {
                      Score = 100;
                  }
              }
              if (Score > selectedPiece) {//assures best move taken depending on score in stack
                  selectedPiece = Score;
                  bestMove = normalMove;
              }
          }
          black = (Stack) blackPsb.clone();
      }
      if (selectedPiece > 0) {//move is taken by ai
          System.out.println("Best move selected by AI agent:" + selectedPiece);
          return bestMove;
      }
      return randomMove(white);//or it is random
  }
  public Move twoLevelsDeep(Stack possibilities){
   System.out.print("This is next two levels deep");
   return null;
  }
}
