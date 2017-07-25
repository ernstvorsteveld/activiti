package org.vorstdev.activiti;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ernstvorsteveld on 29/06/17.
 */
public class UserContext {

    private static ThreadLocal<Map<RequestHeader, String>> localHeaders = new ThreadLocal<>();

    public static void setHeaders(HttpServletRequest httpServletRequest) {
        Map<RequestHeader, String> headers = Maps.newHashMap();
        for (RequestHeader current : RequestHeader.values()) {
            Optional<String> headerValue = current.getRequestHeader(httpServletRequest);
            if (headerValue.isPresent()) {
                headers.put(current, headerValue.get());
            }
        }
        localHeaders.set(headers);
    }

    public static Optional<String> getSegment() {
        return getHeaderValue(RequestHeader.Segment);
    }

    public static Optional<String> getBrand() {
        return getHeaderValue(RequestHeader.Brand);
    }

    public static Optional<String> getHost() {
        return getHeaderValue(RequestHeader.Host);
    }

    public static Optional<String> getXHost() {
        return getHeaderValue(RequestHeader.XHost);
    }

    public static Optional<String> getLocale() {
        return getHeaderValue(RequestHeader.Locale);
    }

    private static Optional<String> getHeaderValue(RequestHeader requestHeader) {
        final String option = localHeaders.get().get(requestHeader);
        if (option == null || option.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(option);
    }

    public static void clear() {
        localHeaders.remove();
    }

}
