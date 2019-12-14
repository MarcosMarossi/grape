package com.example.grapeproject.Help;

public class Base64 {

    public static String codeBase(String texto){
        return android.util.Base64.encodeToString( texto.getBytes(), android.util.Base64.DEFAULT ).replaceAll( "(\\n|\\r)","" );

    }

    public static String decodeBase(String textoCodificado){
        return new String( android.util.Base64.decode( textoCodificado, android.util.Base64.DEFAULT) );
    }
}
