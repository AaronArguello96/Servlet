/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.ArrayList;


public class MenuApp {

    public static ArrayList<String> menuInicial = new ArrayList<>();
    public static ArrayList<String> menuEnvios = new ArrayList<>();
    public static ArrayList<String> menuRecepcion = new ArrayList<>();
    public static ArrayList<String> menuEmpleado = new ArrayList<>();

    public static void cargarMenus() {
    	menuInicial.add("+-----MENU-----+");
        menuInicial.add("1.- Insertar dato");
        menuInicial.add("2.- Borrar dato");
        menuInicial.add("3.- Actualizar dato");
        menuInicial.add("4.- Leer dato");
        menuInicial.add("5.- Consultar empleado por código departamento");
        menuInicial.add("6.- Consultar empleados por edad");
    }
}
