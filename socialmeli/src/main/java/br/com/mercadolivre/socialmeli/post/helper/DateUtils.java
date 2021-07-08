package br.com.mercadolivre.socialmeli.post.helper;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    
    public static Date now() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -14);
        return calendar.getTime();
    }
}
