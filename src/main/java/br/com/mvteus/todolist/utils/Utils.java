package br.com.mvteus.todolist.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    /*
     * Objetivo: Pegar todos os nomes das propriedades que são nulas e atribuir para o BeanUtils;
     *
     * A ideia dessa classe é reutilizar em outros lugares que precisarmos realizar o update parcial;
     * */
    public static String[] getNullPropertyNames(Object source) {
        /*
         * BeanWrapper é uma interface que fornece formas de acessar as propriedades de um objeto
         * */
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }

    /*
     * Através da classe getNullPropertyNames obtemos os valores nulos
     * e copiamos/atribuímos para o BeanUtils e assim permitindo
     * ao usuário mesclar propriedades não nulas de dois objetos.
     * */
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
}
