package org.vorstdev.activiti;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Optional;

/**
 * Created by ernstvorsteveld on 29/06/17.
 */
public enum RequestHeader {
    Segment("X-IW-SEGMENT") {
        @Override
        public Optional<String> getRequestHeader(HttpServletRequest request) {
            return getHeaderValue(request, this.headerName);
        }
    },
    Brand("X-IW-BRAND") {
        @Override
        public Optional<String> getRequestHeader(HttpServletRequest request) {
            return getHeaderValue(request, this.headerName);
        }
    },
    Host("X-IW-HOST") {
        @Override
        public Optional<String> getRequestHeader(HttpServletRequest request) {
            return getHeaderValue(request, this.headerName);
        }
    },
    XHost("X-IW-XHOST") {
        @Override
        public Optional<String> getRequestHeader(HttpServletRequest request) {
            return getHeaderValue(request, this.headerName);
        }
    },
    Locale("X-IW-LOCALE") {
        @Override
        public Optional<String> getRequestHeader(HttpServletRequest request) {
            return getHeaderValue(request, this.headerName);
        }
    };

    private final String headerName;

    RequestHeader(String headerName) {
        this.headerName = headerName;
    }

    public abstract Optional<String> getRequestHeader(HttpServletRequest request);

    public static Optional<String> getHeaderValue(HttpServletRequest request, String headerName) {
        Enumeration<String> headers = request.getHeaderNames();
        if (headers != null && headers.hasMoreElements()) {
            String headerValue = request.getHeader(headerName);
            if (StringUtils.isBlank(headerValue)) {
                return Optional.of(headerValue);
            }
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }
}
