package fr.eni.clinique.bo;

public class Personnels{
    private int CodePers;
    private String Nom;
    private String MotPasse;
    private String Role;
    private boolean Archive;

    public Personnels(int codePers, String nom, String motPasse, String role, boolean archive) {
        CodePers = codePers;
        Nom = nom;
        MotPasse = motPasse;
        Role = role;
        Archive = archive;
    }

    public Personnels() {
    }

    public int getCodePers() {
        return CodePers;
    }

    public void setCodePers(int codePers) {
        CodePers = codePers;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getMotPasse() {
        return MotPasse;
    }

    public void setMotPasse(String motPasse) {
        MotPasse = motPasse;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public boolean isArchive() {
        return Archive;
    }

    public void setArchive(boolean archive) {
        Archive = archive;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Personnels{" +
                "CodePers=" + CodePers +
                ", Nom=" + Nom +
                ", MotPasse=" + MotPasse +
                ", Role=" + Role +
                ", Archive=" + Archive +
                '}';
    }
}