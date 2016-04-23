package org.swork.common.key;

import java.util.Date;
import org.swork.common.utils.DateUtils;

public class KeyGeneratorContext {
    protected String currentDate;

    public String getCurrentDate() {
        return DateUtils.dateToString(new Date());
    }
}
