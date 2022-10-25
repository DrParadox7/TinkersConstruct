package tconstruct.library.util;

import net.minecraft.entity.player.EntityPlayer;
import tconstruct.util.config.PHConstruct;

public class XpUtils {

    public static double getLevelFromXP(float totalXP){
        double level = 0;
        double xp = Math.floor(totalXP);
        do{
            double cap = xpBarCap(level);
            xp -= cap;
            if (xp < 0)
                break;
            level++;
        }while (true);

        return level;
    }

    public static double getLVLFromXP(float xp){
        if (xp <= 255) {
            return xp  / 17;
        }else if (xp > 272 && xp < 887) {
            return (int) ((Math.sqrt(24 * xp - 5159) + 59) / 6);
        }else if (xp > 825) {
            return (int) ((Math.sqrt(56 * xp - 32511) + 303) / 14);
        }
        return 0;
    }
    public static int getXPFromLevel(int level){
        int totalXP = 0;
        for(int i = 0; i < level; i++){
            totalXP += xpBarCap(i);
        }
        return totalXP;
    }

    public static double xpBarCap(double experienceLevel){
        return experienceLevel >= 30 ? 62 + (experienceLevel - 30) * 7 : (experienceLevel >= 15 ? 17 + (experienceLevel - 15) * 3 : 17);
    }

    public static void deductXP(int amount, EntityPlayer player){

        int effectiveTotal = getXPFromLevel(player.experienceLevel) + (int)(player.experience * (float)xpBarCap(player.experienceLevel));

        int removedXP = Math.min(effectiveTotal, amount);

        int newTotal = effectiveTotal - amount;
        if(newTotal < 0)
            newTotal = 0;

        player.experience = 0.0F;
        player.experienceLevel = 0;
        player.experienceTotal -= removedXP;
        if(player.experienceTotal < 0)
            player.experienceTotal = 0;
        player.addExperience(newTotal);

    }

    public static int XPRepair(int repairValue, int upgrades, int repairCount) {
        int repairPerXp = PHConstruct.repairPerXp;
        int repairModifier = PHConstruct.repairModifier;
        double modModifier = PHConstruct.modModifier;

        if (repairPerXp > 0)
            return (int) ((repairValue * (1 + upgrades * modModifier)) / repairPerXp) * (repairCount * repairModifier);
        else return 0;
    }

    public static int ModCost(){
        int cost = PHConstruct.modifierCost;

        if (cost > 0)
        return getXPFromLevel(cost);
        else return 0;
    }

    }
