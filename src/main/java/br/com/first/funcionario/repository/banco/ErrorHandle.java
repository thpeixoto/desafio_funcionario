package br.com.first.funcionario.repository.banco;


/**
 * <b>ErrorHandle</b>
 * </p>
 * Classe auxiliar para manipulação de erros ao executar os médotos do repository
 * @author Thiago Peixoto
 */
public class ErrorHandle {

    /**
     * <b>ErrorHandle</b>
     * </p>
     * Classe auxiliar para manipulação de erros ao executar os médotos do repository
     * @param executable
     * @param operation
     */
    public static <T> T executeHandler(Executable<T> executable, MetodoBD operation) {
        try {
            return executable.execute();
        } catch (NullPointerException ex) {
            throw new CustomDatabaseException("Chave ou Valor em branco no " + operation, ex);
        } catch (RuntimeException ex) {
            throw new CustomDatabaseException("Erro inesperado no " + operation, ex);
        }
    }

    @FunctionalInterface
    public interface Executable<T> {
        T execute();
    }

    public static class CustomDatabaseException extends RuntimeException {
        public CustomDatabaseException(String message) {
            super(message);
        }

        public CustomDatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
