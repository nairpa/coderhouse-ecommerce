package edu.coderhouse.ecommerce.services.interfaces;

public interface SequenceGenerator {
    /***
     * Función para autogenerar codigos de Sciertos atributos.
     * @param sequenceName
     * @return
     */
    Long generateSequence(String sequenceName);
}
