
public class Bombers extends Turrets
{
    public Bombers(int power, int speed, int cost, int durability, String fileName,String barrelFileName, int range)
    {
        super(100, 1, 1000, 50,
                "file:Assets\\Game\\Turrets\\Turret 3\\Turret_3_base.png",
                "file:Assets\\Game\\Turrets\\Turret 3\\Turret_3_barrel.png",
                "file:Assets\\Game\\explosion.png",100);
    }
    public Bombers()
    {
        this(100, 1, 1000, 50,
                "file:Assets\\Game\\Turrets\\Turret 3\\Turret_3_base.png",
                "file:Assets\\Game\\Turrets\\Turret 3\\Turret_3_barrel.png", 100);
    }
}