package com.sirius.travelpass.crypto;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class PassGenerator {
    public static final String BIP39_ENGLISH_SHA256 = "ad90bf3beb7b0eb7e5acd74727dc0da96e0a280a258354e7293fb7e211ac03db";

    MnemonicCode mc;
    private MnemonicCode computeMnemonicCode(Context context) throws IOException {
        if (mc == null) {
            InputStream wis = context.getAssets().open("BIP39/en.txt");
            if (wis != null) {
                mc = new MnemonicCode(wis, BIP39_ENGLISH_SHA256);
                wis.close();
            }
        }
        return mc;
    }

    public void generateMnenomic(){
        //mc.toMnemonic()
    }
}
