package com.ezequielmenor.tema01.act2;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class VistaAct2 {
    private static final String RutaXML = "/Datasets/empleados.xml";

    public static void main(String[] args) {
        GestorXML2 gestor = new GestorXML2();

        try {
            InputStream is = VistaAct2.class.getResourceAsStream(RutaXML);
            if (is == null){throw new Exception("El recurso XML no fue encontrado");}
            //Pide los datos al gestor
            List<Empleado2> listaEmpleado = gestor.parse(is);

            Map<String, java.util.DoubleSummaryStatistics> estadisticas =
                    gestor.calcularEstadisticas(listaEmpleado);

            System.out.printf("%-20s | %-12s | %-15s%n", "Departamento", "Nº Empleados", "Salario Medio (€)");
            System.out.println("------------------------------------------------------------------");

            //Recorrer mapa
            for(Map.Entry<String, java.util.DoubleSummaryStatistics> entry : estadisticas.entrySet()){
                String departamento = entry.getKey();
                java.util.DoubleSummaryStatistics stats = entry.getValue();

                long numEmpleados = stats.getCount();
                double sueldoMedio = stats.getAverage();

                System.out.printf("%-20s | %-12d | %-15.2f%n", departamento, numEmpleados, sueldoMedio);
            }
            System.out.println("------------------------------------------------------------------");
        } catch (Exception e) {
            System.err.println("Error, nos se pudo completar el listado");
            System.err.println("Causa: " + e.getMessage());
        }
    }
}
