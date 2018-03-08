package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DAORace;

import javax.swing.*;
import java.util.List;

public class RaceManager {

    /**
     * Attribut
     */
    private static DAORace daoRace;

    /**
     * Constructeur
     */
    public RaceManager(){
        daoRace = new DAOFactory().getRaceDAO();
    }

    /*

    INUTILE POUR LE MOMENT
    TODO::A DELETE

    public Race selectByRace(String uneRace){
        Race race = null;

        try{
            race = (Race) daoRace.selectByRace(uneRace);
        }catch (DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les races.", null, JOptionPane.ERROR_MESSAGE);

        }

        return race;
    }
    */
}
