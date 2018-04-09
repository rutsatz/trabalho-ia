package exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author raffa
 */
public class JogadaException extends Exception {

    public JogadaException(String msg) {
        super(msg);
    }

    public JogadaException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
