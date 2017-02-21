/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pak;

/**
 *
 * @author George
 */
public class outofboudsException extends Exception {

    /**
     * Creates a new instance of <code>outofboudsException</code> without detail
     * message.
     */
    public outofboudsException() {
        
    }

    /**
     * Constructs an instance of <code>outofboudsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public outofboudsException(String msg) {
        super(msg);
    }
}
