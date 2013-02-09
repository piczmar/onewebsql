package com.example.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDateEditor extends PropertyEditorSupport {

    private boolean isRequired = false;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public SqlDateEditor(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        java.util.Date d = null;
        if (!this.isRequired && !StringUtils.hasText(text)) {
            setValue(null);
        } else {
            try {
                d = sdf.parse(text);
                setValue(new Date(d.getTime()));
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage());
            }
        }
    }

    public String getAsText() {
        Date value = (java.sql.Date) getValue();
        if (value != null) {
            java.util.Date d = new java.util.Date(value.getTime());
            String dstr = sdf.format(d);
            return dstr;
        } else {
            return "";
        }
    }
}