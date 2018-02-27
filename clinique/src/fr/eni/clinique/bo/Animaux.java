package src.fr.eni.clinique.bo;

public class Animaux {
    private int CodeAnimal;
    private String NomAnimal;
    private String Sexe;
    private String Couleur;
    private String Race;
    private int CodeClient;
    private String Tatouage;
    private String Antecedents;
    private boolean Archive;

    public Animaux(int codeAnimal, String nomAnimal, String sexe, String couleur, String race, int codeClient, String tatouage, String antecedents, boolean archive) {
        CodeAnimal = codeAnimal;
        NomAnimal = nomAnimal;
        Sexe = sexe;
        Couleur = couleur;
        Race = race;
        CodeClient = codeClient;
        Tatouage = tatouage;
        Antecedents = antecedents;
        Archive = archive;
    }

    public Animaux() {
    }

    public int getCodeAnimal() {
        return CodeAnimal;
    }

    public void setCodeAnimal(int codeAnimal) {
        CodeAnimal = codeAnimal;
    }

    public String getNomAnimal() {
        return NomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        NomAnimal = nomAnimal;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String couleur) {
        Couleur = couleur;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public int getCodeClient() {
        return CodeClient;
    }

    public void setCodeClient(int codeClient) {
        CodeClient = codeClient;
    }

    public String getTatouage() {
        return Tatouage;
    }

    public void setTatouage(String tatouage) {
        Tatouage = tatouage;
    }

    public String getAntecedents() {
        return Antecedents;
    }

    public void setAntecedents(String antecedents) {
        Antecedents = antecedents;
    }

    public boolean isArchive() {
        return Archive;
    }

    public void setArchive(boolean archive) {
        Archive = archive;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Animaux{" +
                "CodeAnimal=" + CodeAnimal +
                ", NomAnimal=" + NomAnimal +
                ", Sexe=" + Sexe +
                ", Couleur=" + Couleur +
                ", Race=" + Race +
                ", CodeClient=" + CodeClient +
                ", Tatouage=" + Tatouage +
                ", Antecedents=" + Antecedents +
                ", Archive=" + Archive +
                '}';
    }
}