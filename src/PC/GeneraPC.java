package PC;

import java.util.Scanner;

class Computadora {
    private String marca;
    private String modelo;
    private long codigoBarras;
    private double precioTotal;
    private double porcentajeAumento;
    private double montoFinal;
    private String[][] componentes;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getPorcentajeAumento() {
        return porcentajeAumento;
    }

    public void setPorcentajeAumento(double porcentajeAumento) {
        this.porcentajeAumento = porcentajeAumento;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public String[][] getComponentes() {
        return componentes;
    }

    public void setComponentes(String[][] componentes) {
        this.componentes = componentes;
    }

    public void inicializarComponentes(int cantidad) {
        componentes = new String[cantidad][4];
    }

    public void agregarComponente(int index, String codigo, String denominacion, String precio, String obligatorio) {
        componentes[index][0] = codigo;
        componentes[index][1] = denominacion;
        componentes[index][2] = precio;
        componentes[index][3] = obligatorio;
    }
}

public class GeneraPC {
    static String[][] componentesPc = {
            {"AAA", "Placa Madre", "20000", "S"},
            {"BBB", "Procesador", "25000", "S"},
            {"CCC", "Memoria RAM", "5000", "S"},
            {"DDD", "Placa de Red", "3000", "N"},
            {"EEE", "Disco Rigido SSD", "22000", "S"},
            {"FFF", "Placa de Video", "42000", "N"},
            {"GGG", "Monitor Led 21", "32000", "N"},
            {"HHH", "Monitor Led 25", "41000", "N"},
            {"JJJ", "Kit Teclado - Mouse", "9000", "N"},
            {"KKK", "Gabinete", "6500", "S"},
            {"LLL", "Fuente Alimentación", "6500", "S"},
            {"MMM", "Placa de Sonido", "16500", "N"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Computadora computadora = new Computadora();

        System.out.println("Ingrese la marca de la computadora:");
        computadora.setMarca(scanner.nextLine());

        System.out.println("Ingrese el modelo de la computadora:");
        computadora.setModelo(scanner.nextLine());

        long codigoBarras;
        do {
            System.out.println("Ingrese el codigo de barras (7 a 15 dígitos):");
            codigoBarras = scanner.nextLong();
        } while (String.valueOf(codigoBarras).length() < 7 || String.valueOf(codigoBarras).length() > 15);
        computadora.setCodigoBarras(codigoBarras);

        int cantidadComponentes;
        do {
            System.out.println("Ingrese la cantidad de componentes (5 a 12):");
            cantidadComponentes = scanner.nextInt();
        } while (cantidadComponentes < 5 || cantidadComponentes > 12);

        computadora.inicializarComponentes(cantidadComponentes);
        scanner.nextLine(); 

        int index = 0;
        while (index < cantidadComponentes) {
            System.out.println("Componentes de la Computadora");
            System.out.println("Ingrese el código del componente:");
            String codigo = scanner.nextLine();

            boolean encontrado = false;
            for (String[] componente : componentesPc) {
                if (componente[0].equals(codigo)) {
                    boolean yaAgregado = false;
                    for (String[] agregado : computadora.getComponentes()) {
                        if (agregado != null && agregado[0].equals(codigo)) {
                            yaAgregado = true;
                            break;
                        }
                    }
                    if (yaAgregado) {
                        System.out.println("El componente ya fue agregado anteriormente.");
                    } else {
                        computadora.agregarComponente(index, componente[0], componente[1], componente[2], componente[3]);
                        index++;
                    }
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("El codigo ingresado es incorrecto.");
            }
        }

        double precioTotal = 0;
        int recargo = 0;
        for (String[] componente : computadora.getComponentes()) {
            if (componente != null) {
                precioTotal += Double.parseDouble(componente[2]);
                if (componente[3].equals("S")) {
                    recargo += 1;
                }
            }
        }
        
        double recargoMonto = 0;
        if (recargo < 5) {
            System.out.println("Atencion, 1 o más de los componentes obligatorios no fueron agregados, por este motivo se cobrara un recargo del 20%.");
            recargoMonto = precioTotal * 0.20;
        }
        
        double montoFinal = precioTotal + recargoMonto;
        computadora.setPrecioTotal(precioTotal);
        computadora.setMontoFinal(montoFinal);

        System.out.println("La computadora especificada es:");
        System.out.println("Marca: " + computadora.getMarca());
        System.out.println("Modelo: " + computadora.getModelo());
        System.out.println("Código de Barras: " + computadora.getCodigoBarras());

        System.out.println("Codigo \t Ítem \t Denominacion \t Precio");
        for (String[] componente : computadora.getComponentes()) {
            if (componente != null) {
                System.out.println(componente[0] + " \t " + componente[1] + " \t " + componente[2]);
            }
        }
        
        System.out.println("Total Componentes: " + precioTotal);
        System.out.println("Recargo: " + recargoMonto);
        System.out.println("Monto Final: " + montoFinal);
        
        scanner.close();
    }
}
