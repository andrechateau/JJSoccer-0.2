/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.interfaces;

import models.Gol;

/**
 * Interface que representa aqueles que ouvem quando há um gol. Utilizado no
 * contexto dos gols avisando o Matchscene que houve um gol.
 */
public interface GolListener {

    /**
     * Aviso de que um gol occorreu.
     *
     * @param timeCasa Se o gol ocorrido foi do timeCasa ou não.
     */
    public void onGoal(boolean timeCasa);
}
