package fr.eni.clinique.bo;

public class Personnel {
    private int code;
    private String nom;
    private String motPasse;
    private String role;
    private boolean archive;

    public Personnel(String nom, String motPasse, String role, boolean archive) {
        this.nom = nom;
        this.motPasse = motPasse;
        this.role = role;
        this.archive = archive;
    }

    public Personnel(int code, String nom, String motPasse, String role, boolean archive) {
        this.code = code;
        this.nom = nom;
        this.motPasse = motPasse;
        this.role = role;
        this.archive = archive;
    }

    public Personnel() {
    }

    public int getCodePers() {
        return this.code;
    }

    public void setCodePers(int code) {
        this.code = code;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotPasse() {
        return this.motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isArchive() {
        return this.archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Personnel{" +
                "CodePers=" + this.code +
                ", Nom=" + this.nom +
                ", MotPasse=" + this.motPasse +
                ", Role=" + this.role +
                ", Archive=" + this.archive +
                '}';
    }
}