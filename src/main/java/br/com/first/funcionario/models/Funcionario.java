package br.com.first.funcionario.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/**
 *  <b>Funcionario ( Classe ) </b>
 *  </p>
 * Entidade Funcionario
 * </p>
 *
 * Entidade proposta na aplicação do teste. Esta é a entidade principal da API.
 *
 *
 * @author Thiago Peixoto
 * @version 1.0
 */
public class Funcionario implements BasicModel {

    private Long id;
    private String nome;
    private String designacao;
    private Double salario;
    private String telefone;
    private String endereco;

    /**
     * <b>equals(Object o)</b>
     * </p>
     * Dois funcionários serão considerados iguais quando o atributo ID forem iguais
     * @param o Objeto que deseja comparar, que extende de Funcionario
     * @return  true : quando os dois funcionários tem o mesmo ID, false : quando os campos forem diferentes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}



