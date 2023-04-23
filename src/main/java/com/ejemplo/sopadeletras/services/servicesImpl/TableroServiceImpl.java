package com.ejemplo.sopadeletras.services.servicesImpl;

import com.ejemplo.sopadeletras.dto.TableroDto;
import com.ejemplo.sopadeletras.dto.UbicacionDto;
import com.ejemplo.sopadeletras.persistense.Palabras;
import com.ejemplo.sopadeletras.persistense.Tablero;
import com.ejemplo.sopadeletras.persistense.TableroPalabras;
import com.ejemplo.sopadeletras.repository.PalabrasRepository;
import com.ejemplo.sopadeletras.repository.TableroPalabrasRepository;
import com.ejemplo.sopadeletras.repository.TableroRepository;
import com.ejemplo.sopadeletras.services.TableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class TableroServiceImpl implements TableroService {

    @Autowired
    TableroRepository tableroRepository;

    @Autowired
    PalabrasRepository palabrasRepository;

    @Autowired
    TableroPalabrasRepository tableroPalabrasRepository;

    @Override
    public Tablero addTablero(TableroDto tableroDto) {

        //crear tablero
        char[][] tablero = new char[tableroDto.getAlto()][tableroDto.getAncho()];

        //listado de palabras para el tablero
        List<Palabras> palabrasListado = new ArrayList<>();
        HashMap hashMap = new HashMap<>();


        for (Palabras palabras : generaListaPalabras(tableroDto)) {

            for (int j = 0; j < tableroDto.getAlto() * tableroDto.getAncho(); j++) {
                //ubicar las palabras en el tablero aleatoriamente
                int[] posicion = posicionRandom(tableroDto.getAlto(), tableroDto.getAncho());
                String direccion = generarDireccionRandom(tableroDto);
                //validar posicion
                if (validarPosicion(tablero, palabras.getPalabra(), posicion, tableroDto, direccion)) {
                    //Carga las letras en la matriz en dependencia de la direccion
                    int[] posicionFinal = new int[2];
                    ubicarPalabra(posicion, tableroDto.getAlto(), tableroDto.getAncho(), tablero, palabras.getPalabra(), direccion, posicionFinal);
                    int[] temp = new int[4];
                    temp[0] = posicion[0];
                    temp[1] = posicion[1];
                    temp[2] = posicionFinal[0];
                    temp[3] = posicionFinal[1];
                    hashMap.put(palabras, temp);
                    palabrasListado.add(palabras);
                    break;
                }
            }
        }
        for (int f = 0; f < tableroDto.getAlto(); f++) {
            for (int c = 0; c < tableroDto.getAncho(); c++) {
                System.out.print(tablero[f][c] + "|");
            }
            System.out.println();
        }
        System.out.print("------------------\n");
        //rellenar el tablero con letras aleatoriamente
        rellenarTablero(tablero);

        String listado = "";
        for (int f = 0; f < tableroDto.getAlto(); f++) {
            for (int c = 0; c < tableroDto.getAncho(); c++) {
                if (c == 0) {
                    listado = listado.concat("|" + tablero[f][c] + "|");
                    System.out.print("|" + tablero[f][c] + "|");
                } else if (c == tableroDto.getAncho() - 1) {
                    listado = listado.concat(tablero[f][c] + "|\n");
                    System.out.print(tablero[f][c] + "|");
                } else {
                    listado = listado.concat(tablero[f][c] + "|");
                    System.out.print(tablero[f][c] + "|");
                }
            }
            System.out.println();
        }

        //Se puede hace una factory
        Tablero sopaLetra = new Tablero();
        sopaLetra.setTablero(listado);
        tableroRepository.save(sopaLetra);

        //Se puede hace una factory
        for (Palabras palabras : palabrasListado) {
            TableroPalabras tableroPalabras = new TableroPalabras();
            tableroPalabras.setTablero(sopaLetra);
            tableroPalabras.setPalabras(palabras);
            tableroPalabras.setResuelto(false);
            tableroPalabras.setSr(((int[]) hashMap.get(palabras))[0]);
            tableroPalabras.setSc(((int[]) hashMap.get(palabras))[1]);
            tableroPalabras.setEr(((int[]) hashMap.get(palabras))[2]);
            tableroPalabras.setEc(((int[]) hashMap.get(palabras))[3]);
            tableroPalabrasRepository.save(tableroPalabras);
        }
        return sopaLetra;
    }

    @Override
    public List<Palabras> listTablero(Long idTablero) {
        Tablero tablero = tableroRepository.findTableroById(idTablero);
        List<TableroPalabras> tableroPalabras = tableroPalabrasRepository.findAllByTablero(tablero);
        List<Palabras> palabrasList = new ArrayList<>();
        for (TableroPalabras tableroPalabra : tableroPalabras) {
            palabrasList.add(tableroPalabra.getPalabras());
        }
        return palabrasList;
    }

    /**
     * @param idTablero
     * @return
     */
    @Override
    public String viewTablero(Long idTablero) {
        return tableroRepository.findTableroById(idTablero).getTablero();
    }

    /**
     * @param ubicacionDto Identificador del tablero
     * @return List<Palabras>
     */
    @Override
    public Boolean solvTablero(UbicacionDto ubicacionDto) {
        Tablero tablero = tableroRepository.findTableroById(ubicacionDto.getIdTablero());
        List<TableroPalabras> tableroPalabras = tableroPalabrasRepository.findAllByTablero(tablero);
        for (TableroPalabras tableroPalabra : tableroPalabras) {
            if (
                    ubicacionDto.getSr() == tableroPalabra.getSr() &&
                            ubicacionDto.getSc() == tableroPalabra.getSc() &&
                            ubicacionDto.getEr() == tableroPalabra.getEr() &&
                            ubicacionDto.getEc() == tableroPalabra.getEc()
            ) {
                tableroPalabra.setResuelto(true);
                tableroPalabrasRepository.save(tableroPalabra);
                return true;
            }
        }
        return false;
    }

    private double ramdom(int maximo) {
        return Math.floor(Math.random() * (maximo + 1));
    }

    private void ubicarPalabra(int[] posicion, int FILAS, int COLUMNAS, char[][] tablero, String palabra, String direccion, int[] posicionFinal) {
        //ubicar los char
        for (int h = 0; h < palabra.length(); h++) {
            tablero[posicion[0]][posicion[1]] = palabra.charAt(h);
            if (h == palabra.length() - 1) {
                posicionFinal[0] = posicion[1];
                posicionFinal[1] = posicion[0];
            }
            else if (direccion.equals("izq_a_der")) {
                //direccion
                posicion[1]++;
            }
            else if (direccion.equals("der_a_izq")) {
                //direccion
                posicion[1]--;
            }
            else if (direccion.equals("arr_a_abj")) {
                posicion[0]++;
            }
            else if (direccion.equals("abj_a_arr")) {
                posicion[0]--;
            }
            else if (direccion.equals("diag_se")) {
                posicion[0]++;
                posicion[1]++;
            }
            else if (direccion.equals("diag_so")) {
                posicion[0]++;
                posicion[1]--;
            }
            else if (direccion.equals("diag_ne")) {
                posicion[0]--;
                posicion[1]++;
            }
            else if (direccion.equals("diag_no")) {
                posicion[0]--;
                posicion[1]--;
            }
        }
    }

    private boolean validarPosicion(char[][] tablero, String palabras, int[] posicion, TableroDto tableroDto, String direccion) {
        if (posicion[1] + palabras.length() <= tableroDto.getAncho() && direccion.equals("izq_a_der")) {
            for (int i = 0; i < palabras.length(); i++) {
                char izq_a_der = tablero[posicion[0]][posicion[1] + i];
                if (izq_a_der != '\u0000' && palabras.charAt(i) != izq_a_der) {
                    return false;
                }
            }
            return true;
        }
        if (posicion[1] - palabras.length() >= 0 && direccion.equals("der_a_izq")) {
            for (int i = 0; i < palabras.length(); i++) {
                char der_a_izq = tablero[posicion[0]][posicion[1] - i];
                if (der_a_izq != '\u0000' && palabras.charAt(i) != der_a_izq) {
                    return false;
                }
            }
            return true;
        }
        if (posicion[0] + palabras.length() <= tableroDto.getAlto() && direccion.equals("arr_a_abj")) {
            for (int i = 0; i < palabras.length(); i++) {
                char arr_a_abj = tablero[posicion[0] + i][posicion[1]];
                if (arr_a_abj != '\u0000' && palabras.charAt(i) != arr_a_abj) {
                    return false;
                }
            }
            return true;
        }
        if (posicion[0] - palabras.length() >= 0 && direccion.equals("abj_a_arr")) {
            for (int i = 0; i < palabras.length(); i++) {
                char abj_a_arr = tablero[posicion[0] - i][posicion[1]];
                if (abj_a_arr != '\u0000' && palabras.charAt(i) != abj_a_arr) {
                    return false;
                }
            }
            return true;
        }
        //DIAGONALES
        if (direccion.equals("diag_se") &&
                posicion[0] + palabras.length() <= tableroDto.getAlto() &&
                posicion[1] + palabras.length() <= tableroDto.getAncho()
        ) {
            for (int i = 0; i < palabras.length(); i++) {
                char izq_a_der = tablero[posicion[0] + i][posicion[1] + i];
                if (izq_a_der != '\u0000' && palabras.charAt(i) != izq_a_der) {
                    return false;
                }
            }
            return true;
        }
        if (direccion.equals("diag_so") &&
                posicion[0] + palabras.length() <= tableroDto.getAlto() &&
                posicion[1] - palabras.length() >= 0
        ) {
            for (int i = 0; i < palabras.length(); i++) {
                char der_a_izq = tablero[posicion[0] + i][posicion[1] - i];
                if (der_a_izq != '\u0000' && palabras.charAt(i) != der_a_izq) {
                    return false;
                }
            }
            return true;
        }
        if (direccion.equals("diag_ne") &&
                posicion[0] - palabras.length() >= 0 &&
                posicion[1] + palabras.length() <= tableroDto.getAncho()
        ) {
            for (int i = 0; i < palabras.length(); i++) {
                char izq_a_der = tablero[posicion[0] - i][posicion[1] + i];
                if (izq_a_der != '\u0000' && palabras.charAt(i) != izq_a_der) {
                    return false;
                }
            }
            return true;
        }
        if (direccion.equals("diag_no") &&
                posicion[0] - palabras.length() >= 0 &&
                posicion[1] - palabras.length() >= 0
        ) {
            for (int i = 0; i < palabras.length(); i++) {
                char der_a_izq = tablero[posicion[0] - i][posicion[1] - i];
                if (der_a_izq != '\u0000' && palabras.charAt(i) != der_a_izq) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    private int[] posicionRandom(int alto, int ancho) {
        //generar posicion aleatoria
        int[] posicion = new int[2];
        posicion[0] = (int) ramdom(alto - 1);
        posicion[1] = (int) ramdom(ancho - 1);
        return posicion;
    }

    private void rellenarTablero(char[][] tablero) {
        for (int f = 0; f < tablero.length; f++) {
            for (int c = 0; c < tablero[f].length; c++) {
                if (tablero[f][c] == '\u0000') {
                    tablero[f][c] = generaLetraRandom();
                }
            }
        }
    }

    private char generaLetraRandom() {
        char[] diccionario = {
                'a', 'b', 'c',
                'd', 'e', 'f',
                'á', 'é', 'í',
                'g', 'h', 'i',
                'j', 'k', 'l',
                'm', 'n', 'o',
                'p', 'q', 'r',
                's', 't', 'u',
                'v', 'w', 'x',
                'y', 'z', 'ñ',
                'ó', 'ú'
        };
        return diccionario[(int) ramdom(31)];
    }

    private String generarDireccionRandom(TableroDto tableroDto) {
        ArrayList<String> direccion = new ArrayList<>();
        if (tableroDto.isDer_a_izq()) {
            direccion.add("der_a_izq");
        }
        if (tableroDto.isIzq_a_der()) {
            direccion.add("izq_a_der");
        }
        if (tableroDto.isArr_a_abj()) {
            direccion.add("arr_a_abj");
        }
        if (tableroDto.isAbj_a_arr()) {
            direccion.add("der_a_izq");
        }
        if (tableroDto.isDiagonal()) {
            direccion.add("diag_ne");
            direccion.add("diag_no");
            direccion.add("diag_se");
            direccion.add("diag_so");
        }
        return direccion.get((int) ramdom(direccion.size() - 1));
    }

    private List<Palabras> generaListaPalabras(TableroDto tableroDto) {
        return palabrasRepository.palabrasRandom((int) ((tableroDto.getAlto() + tableroDto.getAncho()) / 1.5));
    }
}
