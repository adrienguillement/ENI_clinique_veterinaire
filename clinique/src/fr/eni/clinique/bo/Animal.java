package fr.eni.clinique.bo;

public class Animal {

    /**
     * Attributs
     */
    private int code;
    private String nom;
    private String sexe;
    private String couleur;
    private Race race = new Race();
    private int codeClient;
    private String tatouage;
    private String antecedents;
    private boolean archive;

    /**
     * Constructeur avec code
     * @param codeAnimal
     * @param nomAnimal
     * @param sexe
     * @param couleur
     * @param race
     * @param codeClient
     * @param tatouage
     * @param antecedents
     * @param archive
     */
    public Animal(int codeAnimal, String nomAnimal, String sexe, String couleur, Race race, int codeClient, String tatouage, String antecedents, boolean archive) {
        this.code = codeAnimal;
        this.nom = nomAnimal;
        this.sexe = sexe;
        this.couleur = couleur;
        this.race = race;
        this.codeClient = codeClient;
        this.tatouage = tatouage;
        this.antecedents = antecedents;
        this.archive = archive;
    }

    /**
     * Constructeur sans code
     * @param nomAnimal
     * @param sexe
     * @param couleur
     * @param race
     * @param codeClient
     * @param tatouage
     * @param antecedents
     * @param archive
     */
    public Animal(String nomAnimal, String sexe, String couleur, Race race, int codeClient, String tatouage, String antecedents, boolean archive) {
        this.nom = nomAnimal;
        this.sexe = sexe;
        this.couleur = couleur;
        this.race = race;
        this.codeClient = codeClient;
        this.tatouage = tatouage;
        this.antecedents = antecedents;
        this.archive = archive;
    }

    public Animal() {
    }


    /**
     * GETTERS AND SETTERS
     */

    public int getCodeAnimal() {
        return this.code;
    }

    public void setCodeAnimal(int codeAnimal) {
        this.code = codeAnimal;
    }

    public String getNomAnimal() {
        return nom;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nom = nomAnimal;
    }

    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Race getRace() {
        return this.race;
    }

    public void setRace(String race) {
        this.race.setRace(race);
    }

    public int getCodeClient() {
        return this.codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public String getTatouage() {
        return this.tatouage;
    }

    public void setTatouage(String tatouage) {
        this.tatouage = tatouage;
    }

    public String getAntecedents() {
        return this.antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public boolean isArchive() {
        return this.archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Animaux{" +
                "CodeAnimal=" + this.code +
                ", NomAnimal=" + this.nom +
                ", Sexe=" + this.sexe +
                ", Couleur=" + this.couleur +
                ", Race=" + this.race +
                ", CodeClient=" + this.codeClient +
                ", Tatouage=" + this.tatouage +
                ", Antecedents=" + this.antecedents +
                ", Archive=" + this.archive +
                '}';
    }
}