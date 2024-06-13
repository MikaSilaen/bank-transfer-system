package id.co.task.banktransfersystem.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Mika Silaen
 * @created on 6/13/2024
 */
public class GsonUtils {
    public static Gson gson = new Gson();
    private static final Gson gInstance = new GsonBuilder()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }

                @Override
                public boolean shouldSkipField(FieldAttributes field) {
                    return field.getName().contains("stackTrace");
                }
            })
            .create();
    public synchronized static Gson getInstance() {
        return gInstance;
    }
}
