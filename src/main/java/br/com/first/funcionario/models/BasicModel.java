package br.com.first.funcionario.models;

/**
 * <b>BasicModel ( Interface ) </b>
 * </p>
 * Interface auxiliar das entidades
 * </p>
 *
 * Com a finalidade de obter o id no repository
 *
 *
 * @author Thiago Peixoto
 * @version 1.0
 */
public  interface  BasicModel {

    /**
     * <b>setId(Long id )</b>
     * </p>
     * Setter do ID
     * @param id Valor Long do ID
     */
    public void setId(Long id);

    /**
     * <b>getId()</b>
     * </p>
     * Getter do ID
     * @return  id Valor Long do ID
     */
    public Long getId();


    /**
     * <b>isIdEmpty()</b>
     * </p>
     * Metodo Default que verifica se o ID tem um valor considera vazio, como null ou zero (0);
     * @return  true : valor vazio ( null or zero ) , false : valor > 0
     */
    public default boolean isIdEmpty() {
        return (getId() == null || getId() ==0L);
    }
}
