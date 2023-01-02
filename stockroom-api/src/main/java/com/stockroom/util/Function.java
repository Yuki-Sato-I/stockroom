package com.stockroom.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.Objects;

public class Function {
    /**
     * 指定されたcookieにセットされている値を返す.
     *
     * @param request
     * @param key     取得したいcookieのkey
     * @return 取得したcookie値(ない場合空文字)
     */
    public static String readCookie(final HttpServletRequest request, final String key) {
        final Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if (Objects.equals(key, cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }

    /**
     * cookieを作成する.(有効期限は30日).
     *
     * @param key   cookieのkey
     * @param value cookieのvalue
     * @return 設定されたcookie
     */
    public static Cookie makeCookie(final String key, final String value) {
        final Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }

    /**
     * 引数に与えられたkeyのcookieを削除する.
     *
     * @param key cookieのkey
     * @return 対象keyの有効期限を0にしたcookie
     */
    public static Cookie removeCookie(final String key) {
        final Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }

    /**
     * tokenを生成する.
     * @return 生成されたランダムな文字列.
     * @throws NoSuchAlgorithmException
     */
    public static String generateToken() throws NoSuchAlgorithmException {
        byte[] token = new byte[32];
        final StringBuffer buf = new StringBuffer();
        final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.nextBytes(token);

        for (int i = 0; i < token.length; i++) {
            buf.append(String.format("%02x", token[i]));
        }
        return buf.toString();
    }
}
