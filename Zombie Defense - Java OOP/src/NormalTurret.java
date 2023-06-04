
class NormalTurret extends Turrets
{
    public NormalTurret(int power, int speed, int cost, int durability, String fileName, String barrelFileName, int range)
    {
        super(10, 50, 100, 1000,
                "file:Assets\\Game\\Turrets\\Turret 1\\Turret_1_base.png",
                "file:Assets\\Game\\Turrets\\Turret 1\\Turret_1_barrel.png",
                "file:Assets\\Game\\explosion.png",
                1000);
    }
    public NormalTurret()
    {
        this(10, 50, 100, 1000,
                "file:Assets\\Game\\Turrets\\Turret 1\\Turret_1_base.png",
                "file:Assets\\Game\\Turrets\\Turret 1\\Turret_1_barrel.png",
                1000);
    }
}
