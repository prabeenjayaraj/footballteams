package com.mywork.footballteams.utils;

/**
 * Sourced from StackOverflow
 * Created by prabeenjayaraj on 24/06/2017.
 */
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtilsBean;

public class NullAwareBeanUtilsBean extends BeanUtilsBean{

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null)return;
        super.copyProperty(dest, name, value);
    }

}