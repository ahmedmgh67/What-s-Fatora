package com.bumptech.glide.gifencoder;

class NeuQuant
{
  protected static final int alphabiasshift = 10;
  protected static final int alpharadbias = 262144;
  protected static final int alpharadbshift = 18;
  protected static final int beta = 64;
  protected static final int betagamma = 65536;
  protected static final int betashift = 10;
  protected static final int gamma = 1024;
  protected static final int gammashift = 10;
  protected static final int initalpha = 1024;
  protected static final int initrad = 32;
  protected static final int initradius = 2048;
  protected static final int intbias = 65536;
  protected static final int intbiasshift = 16;
  protected static final int maxnetpos = 255;
  protected static final int minpicturebytes = 1509;
  protected static final int ncycles = 100;
  protected static final int netbiasshift = 4;
  protected static final int netsize = 256;
  protected static final int prime1 = 499;
  protected static final int prime2 = 491;
  protected static final int prime3 = 487;
  protected static final int prime4 = 503;
  protected static final int radbias = 256;
  protected static final int radbiasshift = 8;
  protected static final int radiusbias = 64;
  protected static final int radiusbiasshift = 6;
  protected static final int radiusdec = 30;
  protected int alphadec;
  protected int[] bias = new int['Ā'];
  protected int[] freq = new int['Ā'];
  protected int lengthcount;
  protected int[] netindex = new int['Ā'];
  protected int[][] network;
  protected int[] radpower = new int[32];
  protected int samplefac;
  protected byte[] thepicture;
  
  public NeuQuant(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.thepicture = paramArrayOfByte;
    this.lengthcount = paramInt1;
    this.samplefac = paramInt2;
    this.network = new int['Ā'][];
    paramInt1 = 0;
    while (paramInt1 < 256)
    {
      this.network[paramInt1] = new int[4];
      paramArrayOfByte = this.network[paramInt1];
      paramInt2 = (paramInt1 << 12) / 256;
      paramArrayOfByte[2] = paramInt2;
      paramArrayOfByte[1] = paramInt2;
      paramArrayOfByte[0] = paramInt2;
      this.freq[paramInt1] = 256;
      this.bias[paramInt1] = 0;
      paramInt1 += 1;
    }
  }
  
  protected void alterneigh(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = paramInt2 - paramInt1;
    int k = i;
    if (i < -1) {
      k = -1;
    }
    paramInt1 = paramInt2 + paramInt1;
    int m = paramInt1;
    if (paramInt1 > 256) {
      m = 256;
    }
    int j = 1;
    i = paramInt2 - 1;
    paramInt1 = paramInt2 + 1;
    paramInt2 = j;
    Object localObject;
    int n;
    if ((paramInt1 < m) || (i > k))
    {
      localObject = this.radpower;
      j = paramInt2 + 1;
      n = localObject[paramInt2];
      if (paramInt1 >= m) {
        break label295;
      }
      localObject = this.network;
      paramInt2 = paramInt1 + 1;
      localObject = localObject[paramInt1];
    }
    label295:
    for (;;)
    {
      try
      {
        localObject[0] -= (localObject[0] - paramInt3) * n / 262144;
        localObject[1] -= (localObject[1] - paramInt4) * n / 262144;
        localObject[2] -= (localObject[2] - paramInt5) * n / 262144;
        paramInt1 = paramInt2;
      }
      catch (Exception localException2)
      {
        paramInt1 = paramInt2;
        continue;
        paramInt2 = j;
      }
      if (i > k)
      {
        localObject = this.network;
        paramInt2 = i - 1;
        localObject = localObject[i];
        try
        {
          localObject[0] -= (localObject[0] - paramInt3) * n / 262144;
          localObject[1] -= (localObject[1] - paramInt4) * n / 262144;
          localObject[2] -= (localObject[2] - paramInt5) * n / 262144;
          i = paramInt2;
          paramInt2 = j;
        }
        catch (Exception localException1)
        {
          i = paramInt2;
          paramInt2 = j;
        }
        break;
        return;
      }
      break;
    }
  }
  
  protected void altersingle(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int[] arrayOfInt = this.network[paramInt2];
    arrayOfInt[0] -= (arrayOfInt[0] - paramInt3) * paramInt1 / 1024;
    arrayOfInt[1] -= (arrayOfInt[1] - paramInt4) * paramInt1 / 1024;
    arrayOfInt[2] -= (arrayOfInt[2] - paramInt5) * paramInt1 / 1024;
  }
  
  public byte[] colorMap()
  {
    byte[] arrayOfByte = new byte['̀'];
    int[] arrayOfInt = new int['Ā'];
    int i = 0;
    while (i < 256)
    {
      arrayOfInt[this.network[i][3]] = i;
      i += 1;
    }
    i = 0;
    int j = 0;
    while (i < 256)
    {
      int k = arrayOfInt[i];
      int m = j + 1;
      arrayOfByte[j] = ((byte)this.network[k][0]);
      j = m + 1;
      arrayOfByte[m] = ((byte)this.network[k][1]);
      arrayOfByte[j] = ((byte)this.network[k][2]);
      i += 1;
      j += 1;
    }
    return arrayOfByte;
  }
  
  protected int contest(int paramInt1, int paramInt2, int paramInt3)
  {
    int m = Integer.MAX_VALUE;
    int j = Integer.MAX_VALUE;
    int n = -1;
    int k = -1;
    int i = 0;
    while (i < 256)
    {
      arrayOfInt = this.network[i];
      int i2 = arrayOfInt[0] - paramInt1;
      int i1 = i2;
      if (i2 < 0) {
        i1 = -i2;
      }
      int i3 = arrayOfInt[1] - paramInt2;
      i2 = i3;
      if (i3 < 0) {
        i2 = -i3;
      }
      int i4 = arrayOfInt[2] - paramInt3;
      i3 = i4;
      if (i4 < 0) {
        i3 = -i4;
      }
      i2 = i1 + i2 + i3;
      i1 = m;
      if (i2 < m)
      {
        i1 = i2;
        n = i;
      }
      i2 -= (this.bias[i] >> 12);
      m = j;
      if (i2 < j)
      {
        m = i2;
        k = i;
      }
      j = this.freq[i] >> 10;
      arrayOfInt = this.freq;
      arrayOfInt[i] -= j;
      arrayOfInt = this.bias;
      arrayOfInt[i] += (j << 10);
      i += 1;
      j = m;
      m = i1;
    }
    int[] arrayOfInt = this.freq;
    arrayOfInt[n] += 64;
    arrayOfInt = this.bias;
    arrayOfInt[n] -= 65536;
    return k;
  }
  
  public void inxbuild()
  {
    int n = 0;
    int m = 0;
    int i = 0;
    while (i < 256)
    {
      int[] arrayOfInt1 = this.network[i];
      int i2 = i;
      int j = arrayOfInt1[1];
      int k = i + 1;
      while (k < 256)
      {
        arrayOfInt2 = this.network[k];
        i1 = j;
        if (arrayOfInt2[1] < j)
        {
          i2 = k;
          i1 = arrayOfInt2[1];
        }
        k += 1;
        j = i1;
      }
      int[] arrayOfInt2 = this.network[i2];
      if (i != i2)
      {
        k = arrayOfInt2[0];
        arrayOfInt2[0] = arrayOfInt1[0];
        arrayOfInt1[0] = k;
        k = arrayOfInt2[1];
        arrayOfInt2[1] = arrayOfInt1[1];
        arrayOfInt1[1] = k;
        k = arrayOfInt2[2];
        arrayOfInt2[2] = arrayOfInt1[2];
        arrayOfInt1[2] = k;
        k = arrayOfInt2[3];
        arrayOfInt2[3] = arrayOfInt1[3];
        arrayOfInt1[3] = k;
      }
      int i1 = n;
      k = m;
      if (j != n)
      {
        this.netindex[n] = (m + i >> 1);
        k = n + 1;
        while (k < j)
        {
          this.netindex[k] = i;
          k += 1;
        }
        k = i;
        i1 = j;
      }
      i += 1;
      n = i1;
      m = k;
    }
    this.netindex[n] = (m + 255 >> 1);
    i = n + 1;
    while (i < 256)
    {
      this.netindex[i] = 255;
      i += 1;
    }
  }
  
  public void learn()
  {
    if (this.lengthcount < 1509) {
      this.samplefac = 1;
    }
    this.alphadec = ((this.samplefac - 1) / 3 + 30);
    byte[] arrayOfByte = this.thepicture;
    int i3 = 0;
    int i10 = this.lengthcount;
    int i11 = this.lengthcount / (this.samplefac * 3);
    int i2 = i11 / 100;
    int i4 = 1024;
    int m = 2048;
    int i = 'ࠀ' >> 6;
    int j = i;
    if (i <= 1) {
      j = 0;
    }
    i = 0;
    while (i < j)
    {
      this.radpower[i] = ((j * j - i * i) * 256 / (j * j) * 1024);
      i += 1;
    }
    if (this.lengthcount < 1509) {
      i = 3;
    }
    for (;;)
    {
      int n = 0;
      int i1 = j;
      int i5;
      int i7;
      do
      {
        if (n >= i11) {
          break;
        }
        j = (arrayOfByte[(i3 + 0)] & 0xFF) << 4;
        k = (arrayOfByte[(i3 + 1)] & 0xFF) << 4;
        i5 = (arrayOfByte[(i3 + 2)] & 0xFF) << 4;
        i6 = contest(j, k, i5);
        altersingle(i4, i6, j, k, i5);
        if (i1 != 0) {
          alterneigh(i1, i6, j, k, i5);
        }
        k = i3 + i;
        j = k;
        if (k >= i10) {
          j = k - this.lengthcount;
        }
        i7 = n + 1;
        i5 = i2;
        if (i2 == 0) {
          i5 = 1;
        }
        i2 = i5;
        n = i7;
        i3 = j;
      } while (i7 % i5 != 0);
      int i8 = i4 - i4 / this.alphadec;
      int i9 = m - m / 30;
      m = i9 >> 6;
      int k = m;
      if (m <= 1) {
        k = 0;
      }
      int i6 = 0;
      for (;;)
      {
        i4 = i8;
        i1 = k;
        i2 = i5;
        n = i7;
        i3 = j;
        m = i9;
        if (i6 >= k) {
          break;
        }
        this.radpower[i6] = ((k * k - i6 * i6) * 256 / (k * k) * i8);
        i6 += 1;
      }
      if (this.lengthcount % 499 != 0) {
        i = 1497;
      } else if (this.lengthcount % 491 != 0) {
        i = 1473;
      } else if (this.lengthcount % 487 != 0) {
        i = 1461;
      } else {
        i = 1509;
      }
    }
  }
  
  public int map(int paramInt1, int paramInt2, int paramInt3)
  {
    int k = 1000;
    int j = -1;
    int i2 = this.netindex[paramInt2];
    int i = i2 - 1;
    while ((i2 < 256) || (i >= 0))
    {
      int n = j;
      int i1 = k;
      int m = i2;
      int[] arrayOfInt;
      if (i2 < 256)
      {
        arrayOfInt = this.network[i2];
        n = arrayOfInt[1] - paramInt2;
        if (n < k) {
          break label151;
        }
        m = 256;
        i1 = k;
        n = j;
      }
      for (;;)
      {
        j = n;
        k = i1;
        i2 = m;
        if (i < 0) {
          break;
        }
        arrayOfInt = this.network[i];
        j = paramInt2 - arrayOfInt[1];
        if (j < i1) {
          break label284;
        }
        i = -1;
        j = n;
        k = i1;
        i2 = m;
        break;
        label151:
        i2 += 1;
        m = n;
        if (n < 0) {
          m = -n;
        }
        i1 = arrayOfInt[0] - paramInt1;
        n = i1;
        if (i1 < 0) {
          n = -i1;
        }
        i3 = m + n;
        n = j;
        i1 = k;
        m = i2;
        if (i3 < k)
        {
          n = arrayOfInt[2] - paramInt3;
          m = n;
          if (n < 0) {
            m = -n;
          }
          i3 += m;
          n = j;
          i1 = k;
          m = i2;
          if (i3 < k)
          {
            i1 = i3;
            n = arrayOfInt[3];
            m = i2;
          }
        }
      }
      label284:
      int i3 = i - 1;
      i = j;
      if (j < 0) {
        i = -j;
      }
      k = arrayOfInt[0] - paramInt1;
      j = k;
      if (k < 0) {
        j = -k;
      }
      int i4 = i + j;
      j = n;
      k = i1;
      i2 = m;
      i = i3;
      if (i4 < i1)
      {
        j = arrayOfInt[2] - paramInt3;
        i = j;
        if (j < 0) {
          i = -j;
        }
        i4 += i;
        j = n;
        k = i1;
        i2 = m;
        i = i3;
        if (i4 < i1)
        {
          k = i4;
          j = arrayOfInt[3];
          i2 = m;
          i = i3;
        }
      }
    }
    return j;
  }
  
  public byte[] process()
  {
    learn();
    unbiasnet();
    inxbuild();
    return colorMap();
  }
  
  public void unbiasnet()
  {
    int i = 0;
    while (i < 256)
    {
      int[] arrayOfInt = this.network[i];
      arrayOfInt[0] >>= 4;
      arrayOfInt = this.network[i];
      arrayOfInt[1] >>= 4;
      arrayOfInt = this.network[i];
      arrayOfInt[2] >>= 4;
      this.network[i][3] = i;
      i += 1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\gifencoder\NeuQuant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */