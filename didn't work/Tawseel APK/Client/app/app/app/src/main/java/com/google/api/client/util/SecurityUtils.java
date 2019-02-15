package com.google.api.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import java.util.Iterator;

public final class SecurityUtils
{
  public static KeyStore getDefaultKeyStore()
    throws KeyStoreException
  {
    return KeyStore.getInstance(KeyStore.getDefaultType());
  }
  
  public static KeyStore getJavaKeyStore()
    throws KeyStoreException
  {
    return KeyStore.getInstance("JKS");
  }
  
  public static KeyStore getPkcs12KeyStore()
    throws KeyStoreException
  {
    return KeyStore.getInstance("PKCS12");
  }
  
  public static PrivateKey getPrivateKey(KeyStore paramKeyStore, String paramString1, String paramString2)
    throws GeneralSecurityException
  {
    return (PrivateKey)paramKeyStore.getKey(paramString1, paramString2.toCharArray());
  }
  
  public static KeyFactory getRsaKeyFactory()
    throws NoSuchAlgorithmException
  {
    return KeyFactory.getInstance("RSA");
  }
  
  public static Signature getSha1WithRsaSignatureAlgorithm()
    throws NoSuchAlgorithmException
  {
    return Signature.getInstance("SHA1withRSA");
  }
  
  public static Signature getSha256WithRsaSignatureAlgorithm()
    throws NoSuchAlgorithmException
  {
    return Signature.getInstance("SHA256withRSA");
  }
  
  public static CertificateFactory getX509CertificateFactory()
    throws CertificateException
  {
    return CertificateFactory.getInstance("X.509");
  }
  
  public static void loadKeyStore(KeyStore paramKeyStore, InputStream paramInputStream, String paramString)
    throws IOException, GeneralSecurityException
  {
    try
    {
      paramKeyStore.load(paramInputStream, paramString.toCharArray());
      return;
    }
    finally
    {
      paramInputStream.close();
    }
  }
  
  public static void loadKeyStoreFromCertificates(KeyStore paramKeyStore, CertificateFactory paramCertificateFactory, InputStream paramInputStream)
    throws GeneralSecurityException
  {
    int i = 0;
    paramCertificateFactory = paramCertificateFactory.generateCertificates(paramInputStream).iterator();
    while (paramCertificateFactory.hasNext())
    {
      paramKeyStore.setCertificateEntry(String.valueOf(i), (Certificate)paramCertificateFactory.next());
      i += 1;
    }
  }
  
  public static PrivateKey loadPrivateKeyFromKeyStore(KeyStore paramKeyStore, InputStream paramInputStream, String paramString1, String paramString2, String paramString3)
    throws IOException, GeneralSecurityException
  {
    loadKeyStore(paramKeyStore, paramInputStream, paramString1);
    return getPrivateKey(paramKeyStore, paramString2, paramString3);
  }
  
  public static byte[] sign(Signature paramSignature, PrivateKey paramPrivateKey, byte[] paramArrayOfByte)
    throws InvalidKeyException, SignatureException
  {
    paramSignature.initSign(paramPrivateKey);
    paramSignature.update(paramArrayOfByte);
    return paramSignature.sign();
  }
  
  /* Error */
  public static java.security.cert.X509Certificate verify(Signature paramSignature, javax.net.ssl.X509TrustManager paramX509TrustManager, java.util.List<String> paramList, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws InvalidKeyException, SignatureException
  {
    // Byte code:
    //   0: invokestatic 152	com/google/api/client/util/SecurityUtils:getX509CertificateFactory	()Ljava/security/cert/CertificateFactory;
    //   3: astore 7
    //   5: aload_2
    //   6: invokeinterface 158 1 0
    //   11: anewarray 160	java/security/cert/X509Certificate
    //   14: astore 8
    //   16: iconst_0
    //   17: istore 5
    //   19: aload_2
    //   20: invokeinterface 161 1 0
    //   25: astore_2
    //   26: aload_2
    //   27: invokeinterface 111 1 0
    //   32: ifeq +72 -> 104
    //   35: new 163	java/io/ByteArrayInputStream
    //   38: dup
    //   39: aload_2
    //   40: invokeinterface 119 1 0
    //   45: checkcast 36	java/lang/String
    //   48: invokestatic 169	com/google/api/client/util/Base64:decodeBase64	(Ljava/lang/String;)[B
    //   51: invokespecial 171	java/io/ByteArrayInputStream:<init>	([B)V
    //   54: astore 9
    //   56: aload 7
    //   58: aload 9
    //   60: invokevirtual 175	java/security/cert/CertificateFactory:generateCertificate	(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   63: astore 9
    //   65: aload 9
    //   67: instanceof 160
    //   70: istore 6
    //   72: iload 6
    //   74: ifne +8 -> 82
    //   77: aconst_null
    //   78: areturn
    //   79: astore_0
    //   80: aconst_null
    //   81: areturn
    //   82: aload 8
    //   84: iload 5
    //   86: aload 9
    //   88: checkcast 160	java/security/cert/X509Certificate
    //   91: aastore
    //   92: iload 5
    //   94: iconst_1
    //   95: iadd
    //   96: istore 5
    //   98: goto -72 -> 26
    //   101: astore_0
    //   102: aconst_null
    //   103: areturn
    //   104: aload_1
    //   105: aload 8
    //   107: ldc 52
    //   109: invokeinterface 181 3 0
    //   114: aload_0
    //   115: aload 8
    //   117: iconst_0
    //   118: aaload
    //   119: invokevirtual 185	java/security/cert/X509Certificate:getPublicKey	()Ljava/security/PublicKey;
    //   122: aload_3
    //   123: aload 4
    //   125: invokestatic 188	com/google/api/client/util/SecurityUtils:verify	(Ljava/security/Signature;Ljava/security/PublicKey;[B[B)Z
    //   128: ifeq +11 -> 139
    //   131: aload 8
    //   133: iconst_0
    //   134: aaload
    //   135: areturn
    //   136: astore_0
    //   137: aconst_null
    //   138: areturn
    //   139: aconst_null
    //   140: areturn
    //   141: astore_0
    //   142: goto -40 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	paramSignature	Signature
    //   0	145	1	paramX509TrustManager	javax.net.ssl.X509TrustManager
    //   0	145	2	paramList	java.util.List<String>
    //   0	145	3	paramArrayOfByte1	byte[]
    //   0	145	4	paramArrayOfByte2	byte[]
    //   17	80	5	i	int
    //   70	3	6	bool	boolean
    //   3	54	7	localCertificateFactory	CertificateFactory
    //   14	118	8	arrayOfX509Certificate	java.security.cert.X509Certificate[]
    //   54	33	9	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	5	79	java/security/cert/CertificateException
    //   56	72	101	java/security/cert/CertificateException
    //   104	114	136	java/security/cert/CertificateException
    //   82	92	141	java/security/cert/CertificateException
  }
  
  public static boolean verify(Signature paramSignature, PublicKey paramPublicKey, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws InvalidKeyException, SignatureException
  {
    paramSignature.initVerify(paramPublicKey);
    paramSignature.update(paramArrayOfByte2);
    try
    {
      boolean bool = paramSignature.verify(paramArrayOfByte1);
      return bool;
    }
    catch (SignatureException paramSignature) {}
    return false;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\SecurityUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */