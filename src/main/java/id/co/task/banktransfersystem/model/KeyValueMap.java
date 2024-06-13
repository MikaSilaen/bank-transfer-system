package id.co.task.banktransfersystem.model;

import id.co.task.banktransfersystem.util.StringTools;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
public final class KeyValueMap implements Map<String, Object>, Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Object> map = new HashMap<>(0);

    public String getKeyContainsValue(String value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> StringTools.isTokenizedStrContain((String) entry.getValue(), ";", value))
                .findAny()
                .map(Entry::getKey)
                .orElse(Strings.EMPTY);

    }

    public String getAsString(String key) {
        return (String) get(key);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(String key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Object> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return null;
    }
}
