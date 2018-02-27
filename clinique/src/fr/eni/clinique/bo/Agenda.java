package fr.eni.clinique.bo;

import java.util.Date;

public class Agenda {
    private int CodeVeto;
    private Date DateRdv;
    private int CodeAnimal;

    public Agenda(int codeVeto, Date dateRdv, int codeAnimal) {
        CodeVeto = codeVeto;
        DateRdv = dateRdv;
        CodeAnimal = codeAnimal;
    }

    public Agenda(){

    }

    public int getCodeVeto() {
        return CodeVeto;
    }

    public void setCodeVeto(int codeVeto) {
        CodeVeto = codeVeto;
    }

    public Date getDateRdv() {
        return DateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        DateRdv = dateRdv;
    }

    public int getCodeAnimal() {
        return CodeAnimal;
    }

    public void setCodeAnimal(int codeAnimal) {
        CodeAnimal = codeAnimal;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Agendas{" +
                "CodeVeto=" + CodeVeto +
                ", DateRdv=" + DateRdv +
                ", CodeAnimal=" + CodeAnimal +
                '}';
    }
}