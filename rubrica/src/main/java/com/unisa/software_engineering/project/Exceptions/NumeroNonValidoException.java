
package com.unisa.software_engineering.project.Exceptions;
/**
 * @class NumeriNonValidiException
 * @brief L'eccezione lanciata dal metodo verificaNumeri che effettua il controllo della validità dei numeri di cellulare
 * 
 * Questa eccezione specializza la classe RubricaExceptions
 * 
 * @ingroup Exceptions
 * @author grouppo_19
 * @date 06/12/24
 */
public class NumeroNonValidoException extends ContattoException{
    
    public NumeroNonValidoException(String msg) {
        super(msg);
    }
    
}
