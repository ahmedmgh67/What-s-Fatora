package io.fabric.sdk.android.services.concurrency;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DependsOn
{
  Class<?>[] value();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\io\fabric\sdk\android\services\concurrency\DependsOn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */