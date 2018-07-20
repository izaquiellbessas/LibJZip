/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import jzip.model.ModelZIP;

/**
 *
 * @author izaquiellopesdebessas
 */
public class ListarZIP {

    /**
     * Esta classe retorna uma lista com o conteúdo de um arquivo zippado
     * 
     * @param file Arquivo zippado, o qual deseja-se saber o conteúdo
     * @return Retorna-se uma lista do tipo ModelZIP que contém os arquivos zippados e algumas de suas características
     * @throws FileNotFoundException Excessão por arquivo inexistente
     * @throws IOException Excessão de entrada e saída
     */
    public List<ModelZIP> listZIP(File file) throws FileNotFoundException, IOException {
        List<ModelZIP> list = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file); ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry e;
            
            while ((e = zis.getNextEntry()) != null) {
                ModelZIP model = new ModelZIP(e.getName(), e.getComment(), e.getCompressedSize(),
                        e.getSize(), e.getTime(), e.getCrc(), e.getExtra(), e.getMethod());
                list.add(model);
            }
            
        }
        return list;
    }
}
