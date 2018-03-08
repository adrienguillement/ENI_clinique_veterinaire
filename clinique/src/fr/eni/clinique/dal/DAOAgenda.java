package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

import java.util.List;

public interface DAOAgenda extends DAO{

    public List<Agenda> selectedByPersonnel(Personnel personnel);
}
