package com.mbenz.file.storageservice.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Utils {


    @Test
    public void test_encrypt_decrypt(){

        String originalText = "test string";
        String encryptedTest = AESUtil.encrypt(originalText);
        String decryptedText = AESUtil.decrypt(encryptedTest);

        Assert.assertEquals(originalText, decryptedText);
    }
}
