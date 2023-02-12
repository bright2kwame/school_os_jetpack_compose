package school.os.mobile.app.utils

import android.os.Build
import school.os.mobile.app.BuildConfig

object Constants {
    public final const val API_BASE_URL: String = BuildConfig.BASE_URL;
    public final const val ROUTE_PARAM_USER_ID: String = "userId";
    public final const val ROUTE_PARAM_USER_PHONE: String = "phone";
    public final const val API_DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";

}