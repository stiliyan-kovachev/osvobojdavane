package com.stiliyan.phonebook.phonebook.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Validation {

    public static boolean isValidAddress(CharSequence target) {
        return !TextUtils.isEmpty( target );
    }

    public static boolean isValidPhone(CharSequence target) {
        return !TextUtils.isEmpty( target ) && Patterns.PHONE.matcher( target ).matches();
    }
}
