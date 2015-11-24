package utileria;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Fecha {

    public static String getFecha() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();

        return sdf.format(fecha);

    }
}
