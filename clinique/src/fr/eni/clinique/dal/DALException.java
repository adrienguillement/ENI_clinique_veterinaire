<<<<<<< HEAD
package fr.eni.clinique.dal;
=======
package src.fr.eni.clinique.dal;
>>>>>>> master

public class DALException extends Exception {
    //Constructeurs
    public DALException() {
        super();
    }

    public DALException(String message) {
        super(message);
    }

    public DALException(String message, Throwable exception) {
        super(message, exception);
    }

    //Méthodes
    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer("Couche DAL - ");
        sb.append(super.getMessage());

        return sb.toString() ;
    }
<<<<<<< HEAD

=======
>>>>>>> master
}
