package fr.eni.clinique.bo;

public class Personne {
    private int codePers;
    private String Nom;
    private String MotPasse;
    private String Role;
    private boolean Archive;


    public Personne( String nom, String motPasse, String role, boolean archive) {
        Nom = nom;
        MotPasse = motPasse;
        Role = role;
        Archive = archive;
    }

    public Personne(int codePers, String nom, String motPasse, String role, boolean archive) {
        Nom = nom;
        MotPasse = motPasse;
        Role = role;
        Archive = archive;
    }

    public Personne() {
    }

    public int getCodePers() {
        return codePers;
    }

    public void setCodePers(int codePers) {
        codePers = codePers;
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
        return "Personne{" +
                "CodePers=" + codePers +
                ", Nom=" + Nom +
                ", MotPasse=" + MotPasse +
                ", Role=" + Role +
                ", Archive=" + Archive +
                '}';
    }
}