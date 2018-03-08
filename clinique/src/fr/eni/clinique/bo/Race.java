package fr.eni.clinique.bo;

public class Race{

    /**
     * Attributs
     */
    private String race;
    private String espece;


    /**
     * Constructeur
     * @param race
     * @param espece
     */
    public Race(String race, String espece) {
        this.race = race;
        this.espece = espece;
    }

    public Race() {
    }


    /**
     * GETTERS AND SETTERS
     *
     */

    public String getRace() {
        return this.race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEspece() {
        return this.espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Races{" +
                "Race=" + this.race +
                ", Espece=" + this.espece +
                '}';
    }
}