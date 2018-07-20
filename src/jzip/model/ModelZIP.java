/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jzip.model;

/**
 *
 * @author izaquiellopesdebessas
 */
public class ModelZIP {

    private String nome, comentario;
    private long compressedSize, size, time, crc;
    private byte[] extra;
    private int method;

    /**
     * Construtor padrão
     * 
     * @param nome nome do arquivo
     * @param comentario comentário contido nas propriedades do arquivo
     * @param compressedSize tamanho do arquivo depois de ter sido comprimido
     * @param size tamanho do arquivo sem compressão
     * @param time data em double
     * @param crc código de redundância cíclica
     * @param extra tamanho em bytes
     * @param method método utilizado para a compressão
     */
    public ModelZIP(String nome, String comentario, long compressedSize, long size, long time, long crc, byte[] extra, int method) {
        this.nome = nome;
        this.comentario = comentario;
        this.compressedSize = compressedSize;
        this.size = size;
        this.time = time;
        this.crc = crc;
        this.extra = extra;
        this.method = method;
    }

    /**
     *
     * @return retorna-se o comentário contido nas propriedades do arquivo
     */
    public String getComentario() {
        return comentario;
    }

    /**
     *
     * @param comentario setta um comentário para o arquivo em suas propriedades
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     *
     * @return retorna-se o tamanho do arquivo depois de ter sido comprimido
     */
    public long getCompressedSize() {
        return compressedSize;
    }

    /**
     *
     * @param compressedSize setta um tamanho para um determinado arquivo
     */
    public void setCompressedSize(long compressedSize) {
        this.compressedSize = compressedSize;
    }

    /**
     *
     * @return retorna-se o código de redundância cíclica do arquivo
     */
    public long getCrc() {
        return crc;
    }

    /**
     *
     * @param crc setta o código de redundância cíclica do arquivo
     */
    public void setCrc(long crc) {
        this.crc = crc;
    }

    /**
     *
     * @return retorna-se os bytes do arquivo
     */
    public byte[] getExtra() {
        return extra;
    }

    /**
     *
     * @param extra setta os bytes do arquivo
     */
    public void setExtra(byte[] extra) {
        this.extra = extra;
    }

    /**
     *
     * @return retorna-se o método utilizado para comprimir o arquivo
     */
    public int getMethod() {
        return method;
    }

    /**
     *
     * @param method setta o método utilizado para comprimir o arquivo, ex:
     *
     * ZipEntry.DEFLATED;
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     *
     * @return retorna-se o nome do arquivo com sua extenção
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome setta o nome do arquivo
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return retorna-se o tamanho normal do arquivo, sem compressão
     */
    public long getSize() {
        return size;
    }

    /**
     *
     * @param size setta o tamanho do arquivo
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     *
     * @return retorna-se a data do arquivo, em double
     */
    public long getTime() {
        return time;
    }

    /**
     *
     * @param time setta a data do arquivo
     */
    public void setTime(long time) {
        this.time = time;
    }
}
