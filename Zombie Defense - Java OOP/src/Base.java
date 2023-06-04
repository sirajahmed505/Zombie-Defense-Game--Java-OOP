import java.util.Currency;

public class Base {
    public static int BaseHealth=100;
    public static double GameCurrency;

    public static int getBaseHealth() {
        return BaseHealth;
    }

    public static double getGameCurrency() {
        return GameCurrency;
    }

    public static void setBaseHealth(int baseHealth) {
        BaseHealth = baseHealth;
    }

    public static void setGameCurrency(double gameCurrency) {
        GameCurrency = gameCurrency;
    }
    public static void addCurrency(int money){
        GameCurrency+=money;
    }

    public static void lowhealth(int damage){
        BaseHealth-=damage;
    }
}


