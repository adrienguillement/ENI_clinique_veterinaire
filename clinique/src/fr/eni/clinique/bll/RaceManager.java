package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DAORace;

import java.util.List;

public class RaceManager {
    private static DAORace daoRace;

    public RaceManager(){
        daoRace = new DAOFactory().getRaceDAO();
    }

    public Race selectByRace(String uneRace){
        Race race = null;

        try{
            race = (Race) daoRace.selectByRace(uneRace);
        }catch (DALException e){
            e.printStackTrace();
        }

        return race;
    }
}
