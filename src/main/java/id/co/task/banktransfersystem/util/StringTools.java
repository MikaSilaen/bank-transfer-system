package id.co.task.banktransfersystem.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StringTools {
    public synchronized static boolean isTokenizedStrContain(String strArray, String delimiter, String strTest) {
        return StringTools.getTokensWithCollection(strArray, delimiter).contains(strTest);
    }
    public synchronized static List<String> getTokensWithCollection(String strArray, String delimiter) {
        return Collections.list(new StringTokenizer(strArray, delimiter)).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }
}
