/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.Scanner;

/**
 *
 * @author Javier
 */
public class LibreriaPedirDatos {

    Scanner entrada = new Scanner(System.in);

    public int devolverDatoEntero(String mensaje) {

        int dato;

        System.out.println(mensaje);
        dato = entrada.nextInt();        

        return dato;
    }

    public String devolverDatoCadena() {
        
        String dato;

        System.out.println("Introducir dato");
        dato = entrada.nextLine();

        return null;
    }

}
