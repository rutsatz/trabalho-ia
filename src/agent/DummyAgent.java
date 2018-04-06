package agent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import NineMensMorris.GameInfo;
import NineMensMorris.PlayerAgent;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class DummyAgent implements PlayerAgent {

    @Override
    public String setPiece(GameInfo info) {
        List<String> spots = info.getEmptySpots();

        Random random = new Random();

        int selected = random.nextInt(spots.size());

        return spots.get(selected);
    }

    public String removePiece(GameInfo info) {
        String remove = "";

        List<String> allowedRemoves = info.getAllowedRemoves();

        if (!allowedRemoves.isEmpty()) {
            Random random = new Random();

            int selected = random.nextInt(allowedRemoves.size());

            remove = allowedRemoves.get(selected);

        }

        return remove;
    }

    @Override
    public String movePiece(GameInfo info) {

        String move = "";

        List<String> allowedMoves = info.getAllowedMoves();

        if (!allowedMoves.isEmpty()) {
            Random random = new Random();

            int selected = random.nextInt(allowedMoves.size());

            move = allowedMoves.get(selected);

        }

        return move;
    }

}
