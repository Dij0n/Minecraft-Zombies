package dijon.zombiesbase.playerdata;

import dijon.zombiesbase.shooting.Gun;
import dijon.zombiesbase.shooting.GunType;

public class PlayerData {

    Gun currentlyEquipped;
    int points;
    Status status;

    public PlayerData(){
        currentlyEquipped = GunType.NONE;
        status = Status.IDLE;
        points = 0;
    }


    public void incPoints(int amount){
        points += amount;
    }
    public void decPoints(int amount){
        points -= amount;
    }


    public Gun getGun() {
        return currentlyEquipped;
    }
    public void setGun(Gun currentlyEquipped) {
        this.currentlyEquipped = currentlyEquipped;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

}
