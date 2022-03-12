/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Maugouber
 */
public class Validar {

    //constructor
    public Validar() {
    }
    
    //metodo para comprobar que un campo de texto no este vacio
    public void campoVacio(JTextField campo){
        if(campo.getText().isEmpty()){
            campo.setBackground(Color.red);
        }else{
            campo.setBackground(Color.WHITE);
        }
    }
   
    public void conversionANumeros(JTextField campo){
        try {
            int num = Integer.parseInt(campo.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico");
        }
    }
}
