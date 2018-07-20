/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jzip;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 *
 * @author izaquiellopesdebessas
 */
public class Unzip {

    /**
     * Esta classe realiza a descompressão de arquivos comprimidos com extensão/formato .zip de acordo com as suas especificações
     * 
     * @param zipfile Arquivo zippado que será descomprimido no diretório 'diretorio'
     * @param diretorio Local/URL/caminho onde o arquivo zippando será descomprimido, caso queira, passe uma pasta com o nome do arquivo zippado para que ela seja criada no momento da descompressão
     * @throws FileNotFoundException Possível excessão por falta de arquivo ou diretório não tratada nessa classe
     * @throws IOException Possível excessão de qualquer natureza
     */
    public Unzip(File zipfile, File diretorio) throws FileNotFoundException, IOException {
        byte[] buffer = new byte[2048];

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        if (!diretorio.exists() || !diretorio.isDirectory()) {
            throw new IOException("Diretório inválido");
        } else {
            ZipFile zip = new ZipFile(zipfile);
            Enumeration e = zip.entries();

            while (e.hasMoreElements()) {
                ZipEntry entradas = (ZipEntry) e.nextElement();
                File arquivo = new File(diretorio, entradas.getName());
                //se acaso o diretório não existir, cria a estrutura e pula para próxima entrada
                if (entradas.isDirectory() && !arquivo.exists()) {
                    arquivo.mkdirs();
                    continue;
                }
                //se a estrutura de diretórios não existir, cria
                if (!arquivo.getParentFile().exists()) {
                    arquivo.getParentFile().mkdirs();
                }

                entradas.setMethod(ZipEntry.STORED);
                InputStream is = zip.getInputStream(entradas);
                OutputStream os = new FileOutputStream(arquivo);

                if (is == null) {
                    throw new ZipException("Erro na leitura do arquivo " + entradas.getName());
                }

                int bytes;
                while ((bytes = is.read(buffer)) > 0) {
                    os.write(buffer, 0, bytes);
                }
            }
        }
    }
}
