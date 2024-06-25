package br.com.first.funcionario.controller;

/**
 * <b>BasicController</b>
 * </p>
 * Interface para padronizar os metodos principais dos controllers
 * @author Thiago Peixoto
 */
public interface  BasicController<C> {

    /**
     * <b>salvar</b>
     * </p>
     * Inclusão do registro no banco de dados.
     * @param objectInsert Recebe um objeto que extende de BasicModel, e encaminha para o service incluir o ID
     * @return  C  com os dados do parametro, com o novo ID, caso  o ID do parametro for null;
     */
    public C salvar(C objectInsert );

    /**
     * <b>buscar</b>
     * </p>
     * Busca do registro no banco de dados.
     * @param  id Busca do registro pela chave primária
     * @return C os dados encontrados no banco sem manipulação
     */
    public C buscar ( Long id);

    /**
     * <b>atualizar</b>
     * </p>
     * Alteração do registro no banco de dados.
     * @param  objectUpdate Registro a ser alterado sendo obrigatório ter a chave primária preenchida
     */
    public void  atualizar ( C objectUpdate );


    /**
     * <b>excluir</b>
     * </p>
     * Exclusão do registro no banco de dados.
     * @param  id Chave Primária do Registro a ser excluído.
     */
    public void  excluir (Long id );



}
