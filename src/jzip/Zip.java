/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jzip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author izaquiellopesdebessas
 */
public class Zip {

    /**
     * Esta classe realiza a compressão de arquivos de qualquer tipo para um arquivo .zip
     * 
     * @param filezip Arquivo .zip que será criado, ou que já exista e que terá outros arquivos adicionados ao seu conteúdo
     * @param files Arquivos a serem comprimidos e que ficaram dentro do arquivo zippado
     * @return Lista com os arquivos adicionados ao zip (comprimidos)
     * @throws FileNotFoundException Excessão por inexistência de arquivo ou diretório
     * @throws IOException Excessão de entrada ou saída
     * @throws Exception Excessão de qualquer natureza
     */
    public List zippar(File filezip, File[] files) throws FileNotFoundException, IOException, Exception {
        if (!filezip.getName().toLowerCase().endsWith(".zip")) {
            filezip = new File(filezip.getAbsoluteFile() + ".zip");
        }

        FileOutputStream fos = new FileOutputStream(filezip);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        List list = zippando(bos, files);

        return list;
    }

    private List zippando(OutputStream os, File[] files) throws Exception {
        if (files == null || files.length < 1) {
            throw new Exception("É necessário passar no mínimo um arquivo para ser zippado.");
        }

        List list = new ArrayList();
        ZipOutputStream zos = new ZipOutputStream(os);

        for (File file : files) {
            String url = file.getParent();
            List novas = AddFileZIP(zos, file, url);
            if (novas != null) {
                list.addAll(novas);
            }
        }

        return list;
    }

    private List AddFileZIP(ZipOutputStream zos, File file, String url) throws IOException {
        byte[] buffer = new byte[2048];
        List list = new ArrayList();

        if (file.isDirectory()) {
            File[] arquivos = file.listFiles();

            for (File arquivo : arquivos) {
                List novas = AddFileZIP(zos, arquivo, url);
                if (novas != null) {
                    list.addAll(novas);
                }
            }
            return list;
        }

        String urlZip = "";
        int idx = file.getAbsolutePath().indexOf(url);
        if (idx >= 0) {
            //calcula os dirertórios a partir do diretório inicial
            urlZip = file.getAbsolutePath().substring(idx + url.length() + 1);
        }

        ZipEntry zipentry = new ZipEntry(urlZip);
        zos.putNextEntry(zipentry);
        zos.setMethod(ZipOutputStream.DEFLATED);

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        int bytes;
        while ((bytes = bis.read(buffer, 0, 2048)) != -1) {
            zos.write(buffer, 0, bytes);
        }

        list.add(zipentry);

        return list;
    }
}
