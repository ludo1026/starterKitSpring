package org.telosys.starterkits.web;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.expression.spel.ast.FormatHelper;
import org.springframework.web.servlet.LocaleResolver;

public class LocalizedDateEditor extends CustomDateEditor {

    public LocalizedDateEditor(SimpleDateFormat sdf) {
        super(sdf, true);
    }
    
}
