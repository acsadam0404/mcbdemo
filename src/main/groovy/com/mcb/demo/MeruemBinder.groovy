package com.mcb.demo

import com.vaadin.data.BeanValidationBinder
import com.vaadin.data.HasValue
import com.vaadin.data.ValueProvider
import com.vaadin.data.converter.StringToLongConverter
import com.vaadin.server.Setter
import com.vaadin.ui.AbstractTextField

/**
 * Created by TamasTurcsek on 2017. 06. 21..
 */
class MeruemBinder<T> extends BeanValidationBinder<T> {

    protected final T beanClass

    MeruemBinder(Class<T> beanType) {
        super(beanType)
        this.beanClass = beanType
    }

    MeruemBinder(T bean) {
        this(bean.class)
        setBean(bean)
    }

    @Override
    void bindInstanceFields(Object objectWithMemberFields) {
        bindProperties(objectWithMemberFields, beanClass?.metaClass?.properties, "")
        super.bindInstanceFields(objectWithMemberFields)
    }

    /* baromi nagy hack, hogy ne tegye bele a nested propertyket a BeanPropertySet-be */
    public void bindNestedBean(Object objectWithMemberFields, String nestedBeanPropertyName, Class nestedBean) {
        nestedBean?.metaClass?.properties?.each { prop ->
            def field
            try {
                field = objectWithMemberFields.getAt(prop.name + "Field") as HasValue
                String name = prop.name
                if (nestedBeanPropertyName) {
                    name = nestedBeanPropertyName + "." + prop.name
                }
                if (field) {
                   if (prop.type == String && field instanceof AbstractTextField) {
                        forField(field).bind(new ValueProvider<T, String>() {
                            @Override
                            String apply(T t) {
                                return  t."${nestedBeanPropertyName}"."${prop.name}"
                            }
                        }, new Setter<T, String>() {
                            @Override
                            void accept(T t, String s) {
                                t."${nestedBeanPropertyName}"."${prop.name}" = s
                            }
                        })
                    }
                }
            } catch (MissingPropertyException e) {
                //NOP
            }
        }
    }

    private void bindProperties(Object objectWithMemberFields, List<MetaProperty> properties, String namePrefix) {
        properties?.each { prop ->
            def field
            try {
                field = objectWithMemberFields.getAt(prop.name + "Field") as HasValue
                String name = prop.name
                if (namePrefix) {
                    name = namePrefix + "." + prop.name
                }
                if (field) {
                   if(prop.type == long || prop.type == Long) {
                        forField(field).withConverter(new StringToLongConverter("Can't convert String to Long")).bind(name)
                    }
                    else if (prop.type == String && field instanceof AbstractTextField) {
                        forField(field).bind(name)
                    }
                }
            } catch (MissingPropertyException e) {
                //NOP
            }
        }
    }
}
