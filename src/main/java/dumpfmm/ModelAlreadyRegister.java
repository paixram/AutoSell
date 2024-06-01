/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package dumpfmm;

/**
 *
 * @author Luizzz
 */
public class ModelAlreadyRegister extends Exception {

    /**
     * Creates a new instance of <code>ModelAlreadyRegister</code> without
     * detail message.
     */
    public ModelAlreadyRegister() {
    }

    /**
     * Constructs an instance of <code>ModelAlreadyRegister</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ModelAlreadyRegister(String msg) {
        super(msg);
    }
}
