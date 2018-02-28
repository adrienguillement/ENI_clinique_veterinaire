package fr.eni.clinique.bo;

public class Client{
    private int code;
    private String nom;
    private String prenom;
    private String adresse1;
    private String adresse2;
    private String codePostal;
    private String ville;
    private String numTel;
    private String assurance;
    private String email;
    private String remarque;
    private boolean archive;

    public Client(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2, String codePostal, String ville, String numTel, String assurance, String email, String remarque, boolean archive) {
        this.code= codeClient;
        this.nom = nomClient;
        this.prenom = prenomClient;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.codePostal = codePostal;
        this.ville = ville;
        this.numTel = numTel;
        this.assurance = assurance;
        this.email = email;
        this.remarque = remarque;
        this.archive = archive;
    }

    public Client() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int codeClient) {
        this.code = codeClient;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nomClient) {
        this.nom = nomClient;
    }

    public String getPrenomClient() {
        return this.prenom;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenom = prenomClient;
    }

    public String getAdresse1() {
        return this.adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return this.adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNumTel() {
        return this.numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAssurance() {
        return this.assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarque() {
        return this.remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public boolean isArchive() {
        return this.archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Clients{" +
                "CodeClient=" + this.code +
                ", NomClient=" + this.nom +
                ", PrenomClient=" + this.prenom +
                ", Adresse1=" + this.adresse1 +
                ", Adresse2=" + this.adresse2 +
                ", CodePostal=" + this.codePostal +
                ", Ville=" + this.ville +
                ", NumTel=" + this.numTel +
                ", Assurance=" + this.assurance +
                ", Email=" + this.email +
                ", Remarque=" + this.remarque +
                ", Archive=" + this.archive +
                '}';
    }
}