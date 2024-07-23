package org.giocodelloca;

import org.giocodelloca.effects.CellEffect;
import org.giocodelloca.effects.VictoryEffect;

import java.util.*;

public class CellEffectManager {
    public static final Map<Integer, CellEffect> effects = new HashMap<>();
    private static final Random random = new Random();

    public static void configureEffects(Map<CellEffect, Integer> effectSettings) {
        effects.clear();
        effects.put(62, new VictoryEffect());

        for (Map.Entry<CellEffect, Integer> entry : effectSettings.entrySet()) {
            CellEffect effect = entry.getKey();
            int count = entry.getValue();
            addRandomEffects(count, effect);
        }
    }

    private static void addRandomEffects(int count, CellEffect effect) {
        Set<Integer> uniquePositions = new HashSet<>();
        while (uniquePositions.size() < count) {
            int pos = random.nextInt(5, 61);
            if (!uniquePositions.contains(pos) && !effects.containsKey(pos)) {
                uniquePositions.add(pos);
                effects.put(pos, effect);
            }
        }
    }

    public static CellEffect getEffect(int position) {
        return effects.get(position);
    }

    public static Map<Integer, CellEffect> getAllEffects() {
        return effects;
    }
}