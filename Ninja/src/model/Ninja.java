package model;

/**
 * Created by q on 12/14/2016.
 */
public class Ninja {
    private String name;
    private String village;
    private int atk;
    private int def;
    private int hp;


    public Ninja(String name, String village, int atk, int def, int hp) {
        this.name = name;
        this.village = village;
        this.atk = atk;
        this.def = def;
        this.hp = hp;
    }

    public Ninja() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public static Ninja attack(Ninja n1, Ninja n2) {
        while (n1.getHp() > 0 && n2.getHp() > 0) {
            int dmg = n1.getAtk() - n2.getDef();
            if (dmg > 0) {
                n2.setHp(n2.getHp() - dmg);
            } else if (dmg < 0) {
                n1.setHp(n1.getHp() - dmg);
            }
        }
        if (n1.getHp() > 0) return n1;

        else return n2;
    }
}
