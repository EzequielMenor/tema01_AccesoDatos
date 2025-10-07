package com.ezequielmenor.tema01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.server.ExportException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;

public class Ej3 {
    private static final String RUTA = "/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros/usuario.properties";
    private static final String CLAVE = "password";

    public static String hashSHA1(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean validarContrasena(String pass) {
        return pass.length() >= 8 &&
                pass.matches(".*[A-Z].*") &&
                pass.matches(".*[a-z].*") &&
                pass.matches(".*[0-9].*") &&
                pass.matches(".*[^a-zA-Z0-9].*");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        File f = new File(RUTA);

        if (!f.exists()){
            f.getParentFile().mkdirs();
            prop.setProperty(CLAVE, hashSHA1("S3cret@"));
            prop.store(new FileOutputStream(f), "Archivo de usuario");
        }else {
            prop.load(new FileInputStream(f));
        }

        System.out.println("**********************");
        System.out.println("1. Validar Acceso");
        System.out.println("**********************");
        System.out.println("Elige opción: ");
        int opcion = Integer.parseInt(sc.nextLine());

        if(opcion == 1){
            System.out.println("Introduce la contraseña: ");
            String pass = sc.nextLine();
            if (prop.getProperty(CLAVE).equals(hashSHA1(pass))){
                System.out.println("Acceso correcto");

                boolean salir = false;
                while (!salir) {
                    System.out.println("**********************");
                    System.out.println("1. Modificar Contraseña");
                    System.out.println("2. Salir");
                    System.out.println("**********************");
                    System.out.println("Elige opción: ");
                    int op = Integer.parseInt(sc.nextLine());
                    switch (op){
                        case 1:
                            System.out.println("Contraseña actual: ");
                            String actual = sc.nextLine();
                            if (prop.getProperty(CLAVE).equals(hashSHA1(actual))){
                                System.out.println("Nueva contraseña: ");
                                String nueva = sc.nextLine();
                                if (validarContrasena(nueva)){
                                    prop.setProperty(CLAVE, hashSHA1(nueva));
                                    prop.store(new FileOutputStream(f), "Archivo de usuario");
                                    System.out.println("Contraseña modificada");
                                }else {
                                    System.out.println("Contraseña no válida");
                                }
                            }else {
                                System.out.println("Contraseña incorrecta");
                            }
                            break;

                        case 2:
                            salir = true;
                            break;
                    }
                }
            }else {
                System.out.println("Acceso denegado");
            }
        }


    }
}

