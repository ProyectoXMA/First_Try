public class PowerUp {
    //A powerUp has a powerUpType which is a enum that represents the type of powerUp we are colliding
    private PowerUpType powerUp;
    //The constructor for the powerUp class
    public PowerUp(PowerUpType powerUp){
        this.powerUp = powerUp;
    }
    //Getter for the powerUpType
    public PowerUpType getPowerUpType(){
        return powerUp; //return the powerUpType
    }
}

