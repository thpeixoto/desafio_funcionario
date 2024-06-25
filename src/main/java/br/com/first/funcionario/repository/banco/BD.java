package br.com.first.funcionario.repository.banco;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BD {

    private Map<Long, Object> banco = new HashMap<>();

    public void persist(Long id, Object obj) {
        ErrorHandle.executeHandler(() -> {
            banco.put(id, obj);
            return null;
        }, MetodoBD.persist);
    }

    public boolean merge(Long id, Object obj) {
        return ErrorHandle.executeHandler(() -> {
            banco.put(id, obj);
            return true;
        }, MetodoBD.merge);
    }

    public boolean delete(Long id) {
        return ErrorHandle.executeHandler(() -> {
            banco.remove(id);
            return true;
        }, MetodoBD.delete);
    }

    public Object find(Long id) {
        return ErrorHandle.executeHandler(() -> banco.get(id), MetodoBD.find);
    }

    public boolean existObj(Long id) {
        return ErrorHandle.executeHandler(() -> banco.containsKey(id), MetodoBD.existObj);
    }

    public Map<Long, Object> getBanco() {
        if (banco == null) throw new ErrorHandle.CustomDatabaseException("Conexão Perdida");
        return banco;


    }
}
