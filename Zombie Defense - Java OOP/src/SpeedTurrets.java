
class SpeedTurrets extends Turrets
{
    public SpeedTurrets(int power, int speed, int cost, int durability, String fileName, String barrelFileName, int range)
    {
        super(5, 150, 500, 2000,
                "file:Assets\\Game\\Turrets\\Turret 2\\Turret_2_base.png",
                "file:Assets\\Game\\Turrets\\Turret 2\\Turret_2_barrel.png" ,
                "file:Assets\\Game\\explosion.png",300);
    }
    public SpeedTurrets()
    {
        this(5, 150, 500, 2000,
                "file:Assets\\Game\\Turrets\\Turret 2\\Turret_2_base.png",
                "file:Assets\\Game\\Turrets\\Turret 2\\Turret_2_barrel.png" ,300);
    }
}
