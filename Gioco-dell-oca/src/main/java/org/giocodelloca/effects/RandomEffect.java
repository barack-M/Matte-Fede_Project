package org.giocodelloca.effects;

import javafx.scene.image.Image;
import org.giocodelloca.GameController;
import org.giocodelloca.Player;

import java.util.Objects;
import java.util.Random;

public class RandomEffect implements CellEffect {
    @Override
    public void apply(Player player, GameController controller) {
        Random rand = new Random();
        int n = rand.nextInt(1, 101);
        if(n < 7){
            controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": Vieni scambiato con il giocatore in prima posizione! Se sei te avanza di 4!");
            Player maxPositionPlayer = player;
            for (Player p : controller.players) {
                if (p != player && (p.getPosition() < maxPositionPlayer.getPosition())) {
                    maxPositionPlayer = p;
                }
            }
            if(maxPositionPlayer != player) {
                int tempPosition = player.getPosition();
                player.movePlayerTo(maxPositionPlayer.getPosition());
                maxPositionPlayer.movePlayerTo(tempPosition);
            }else{
                player.movePlayerTo(player.getPosition() + 4);
            }
        }
        else if(n < 15){
            player.stuck += 2;
            controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": ASPETTA DUE TURNI: " + player.getName() + " per i prossimi due turni non ti puoi muovere!");

        }
        else if(n < 58){
            controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": TORNI INDIETRO DI 3 CASELLE: ops " + player.getName() + " sei scivolato all'indietro!");
            player.movePlayerTo(player.position - 3);
        }
        else {
            controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": VAI AVANTI DI 3 CASELLE: che fortuna, " + player.getName() + ", hai trovato una scorciatoia!");
            player.movePlayerTo(player.position + 3);
        }
    }

    @Override
    public Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/giocodelloca/Icons/random.png")));
    }
}
