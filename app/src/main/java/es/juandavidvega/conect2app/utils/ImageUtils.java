package es.juandavidvega.conect2app.utils;

import es.juandavidvega.conect2app.remote.interoperability.RequestSender;

/**
 * Created by JuanDavid on 31/05/2015.
 */
public class ImageUtils {
    public static String getAbsoluteURL(String iconURL) {
        String[] splittedURL = iconURL.split("/");
        String imageName = splittedURL[splittedURL.length - 1];
        return RequestSender.BaseURL + "img/" + imageName;

    }
}
