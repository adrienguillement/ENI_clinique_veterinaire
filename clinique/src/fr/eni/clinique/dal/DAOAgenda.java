package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Personnel;

import java.sql.Timestamp;
import java.util.List;

public interface DAOAgenda extends DAO{
    public List<Agenda> selectedByPersonnel(Personnel personnel);
    public List<Agenda> selectByPersonneId(int id) throws DALException;
    public List<Agenda> selectByDateAndPersonneID(Timestamp date, int id) throws DALException;
}
