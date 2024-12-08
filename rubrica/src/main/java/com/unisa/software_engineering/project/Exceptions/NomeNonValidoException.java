
package com.unisa.software_engineering.project.Exceptions;

/**
 * @class NomeNonValidoException
 * @brief L'eccezione lanciata dal metodo verificaNome che effettua il controllo della validità dei campi nome o cognome
 * 
 * Questa eccezione specializza la classe RubricaExceptions
 * 
 * @ingroup Exceptions
 * @author grouppo_19
 * @date 06/12/24
 */
public class NomeNonValidoException extends ContattoException{
    
    public NomeNonValidoException(String msg) {
        super(msg);
    }
    
}
