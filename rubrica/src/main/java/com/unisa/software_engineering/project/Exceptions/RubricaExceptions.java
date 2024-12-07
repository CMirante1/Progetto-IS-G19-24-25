

package com.unisa.software_engineering.project.Exceptions;

/**
 * @class RubricaExceptions
 * @brief L'eccezione lanciata dai componenti della rubrica
 * 
 * Questa è l'eccezione generica, superclasse di tutte le eccezioni legate alla gestione della rubrica che andranno a specializzarla
 * 
 * @ingroup Exceptions
 * @author grouppo_19
 * @date 06/12/24
 */
public class RubricaExceptions extends Exception {
    
    public RubricaExceptions(String msg) {
        
        super(msg);
    }
}
