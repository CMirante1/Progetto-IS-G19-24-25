/*
 * 
 * 
 * 
 */
package com.unisa.software_engineering.project.Exceptions;

/**
 * @class NomeNonValidoException
 * @brief L'eccezione lanciata dal metodo verificaEmail che effettua il controllo della validità degli indirizzi email
 * 
 * Questa eccezione specializza la classe RubricaExceptions
 * 
 * @ingroup Exceptions
 * @author grouppo_19
 * @date 06/12/24
 */
public class EmailNonValidaException extends RubricaExceptions{
    
    public EmailNonValidaException(String msg) {
        super(msg);
    }
    
}
