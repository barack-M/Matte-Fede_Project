package org.giocodelloca;

import java.util.Random;

public class SpecialCells {

    public static int[] waitCord = new int[6];
    public static int[] backtoOneCord = new int[2];

    public static void initialize(){
        Random random = new Random();
        for(int i = 0; i < 6; i++) {
            waitCord[i] = random.nextInt(1, 61);
        }

        for(int i = 0; i < 2; i++) {
            backtoOneCord[i] = random.nextInt(30, 61);
        }
    }

    public static String activate(Player player){
        for (int i : waitCord) {
            if (player.position == i) {
                waitOne(player);
                return "ASPETTA UN TURNO: " + player.name +  " per il prossimo turno non ti puoi muovere!";
            }
        }
        for (int i : backtoOneCord) {
            if (player.position == i) {
                backtoOne(player);
                return "TORNI ALLA CASELLA UNO: dai " + player.name + " che ce la fai!";
            }
        }
        if(player.position == 62){
            return "Hai vinto " + player.name + "!";
        }
        return "Nessun effetto!";
    }

    private static void waitOne(Player player){
        player.stuck =+ 1;

    }

    private static void backtoOne(Player player){
        player.position = 1;
    }

    public static void victory(Player player){

    }

}
