package com.bumptech.glide.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

class LZWEncoder
{
  static final int BITS = 12;
  private static final int EOF = -1;
  static final int HSIZE = 5003;
  int ClearCode;
  int EOFCode;
  int a_count;
  byte[] accum = new byte['Ā'];
  boolean clear_flg = false;
  int[] codetab = new int['ᎋ'];
  private int curPixel;
  int cur_accum = 0;
  int cur_bits = 0;
  int free_ent = 0;
  int g_init_bits;
  int hsize = 5003;
  int[] htab = new int['ᎋ'];
  private int imgH;
  private int imgW;
  private int initCodeSize;
  int[] masks = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
  int maxbits = 12;
  int maxcode;
  int maxmaxcode = 4096;
  int n_bits;
  private byte[] pixAry;
  private int remaining;
  
  LZWEncoder(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
  {
    this.imgW = paramInt1;
    this.imgH = paramInt2;
    this.pixAry = paramArrayOfByte;
    this.initCodeSize = Math.max(2, paramInt3);
  }
  
  private int nextPixel()
  {
    if (this.remaining == 0) {
      return -1;
    }
    this.remaining -= 1;
    byte[] arrayOfByte = this.pixAry;
    int i = this.curPixel;
    this.curPixel = (i + 1);
    return arrayOfByte[i] & 0xFF;
  }
  
  final int MAXCODE(int paramInt)
  {
    return (1 << paramInt) - 1;
  }
  
  void char_out(byte paramByte, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = this.accum;
    int i = this.a_count;
    this.a_count = (i + 1);
    arrayOfByte[i] = paramByte;
    if (this.a_count >= 254) {
      flush_char(paramOutputStream);
    }
  }
  
  void cl_block(OutputStream paramOutputStream)
    throws IOException
  {
    cl_hash(this.hsize);
    this.free_ent = (this.ClearCode + 2);
    this.clear_flg = true;
    output(this.ClearCode, paramOutputStream);
  }
  
  void cl_hash(int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      this.htab[i] = -1;
      i += 1;
    }
  }
  
  void compress(int paramInt, OutputStream paramOutputStream)
    throws IOException
  {
    this.g_init_bits = paramInt;
    this.clear_flg = false;
    this.n_bits = this.g_init_bits;
    this.maxcode = MAXCODE(this.n_bits);
    this.ClearCode = (1 << paramInt - 1);
    this.EOFCode = (this.ClearCode + 1);
    this.free_ent = (this.ClearCode + 2);
    this.a_count = 0;
    int i = nextPixel();
    int j = 0;
    paramInt = this.hsize;
    while (paramInt < 65536)
    {
      j += 1;
      paramInt *= 2;
    }
    int i1 = this.hsize;
    cl_hash(i1);
    output(this.ClearCode, paramOutputStream);
    paramInt = i;
    for (;;)
    {
      int n = nextPixel();
      if (n == -1) {
        break;
      }
      int i2 = (n << this.maxbits) + paramInt;
      i = n << 8 - j ^ paramInt;
      if (this.htab[i] == i2)
      {
        paramInt = this.codetab[i];
      }
      else
      {
        int k = i;
        if (this.htab[i] >= 0)
        {
          k = i1 - i;
          int m = i;
          if (i == 0)
          {
            k = 1;
            m = i;
          }
          do
          {
            m -= k;
            i = m;
            if (m < 0) {
              i = m + i1;
            }
            if (this.htab[i] == i2)
            {
              paramInt = this.codetab[i];
              break;
            }
            m = i;
          } while (this.htab[i] >= 0);
          k = i;
        }
        output(paramInt, paramOutputStream);
        paramInt = n;
        if (this.free_ent < this.maxmaxcode)
        {
          int[] arrayOfInt = this.codetab;
          i = this.free_ent;
          this.free_ent = (i + 1);
          arrayOfInt[k] = i;
          this.htab[k] = i2;
        }
        else
        {
          cl_block(paramOutputStream);
        }
      }
    }
    output(paramInt, paramOutputStream);
    output(this.EOFCode, paramOutputStream);
  }
  
  void encode(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.initCodeSize);
    this.remaining = (this.imgW * this.imgH);
    this.curPixel = 0;
    compress(this.initCodeSize + 1, paramOutputStream);
    paramOutputStream.write(0);
  }
  
  void flush_char(OutputStream paramOutputStream)
    throws IOException
  {
    if (this.a_count > 0)
    {
      paramOutputStream.write(this.a_count);
      paramOutputStream.write(this.accum, 0, this.a_count);
      this.a_count = 0;
    }
  }
  
  void output(int paramInt, OutputStream paramOutputStream)
    throws IOException
  {
    this.cur_accum &= this.masks[this.cur_bits];
    if (this.cur_bits > 0) {}
    for (this.cur_accum |= paramInt << this.cur_bits;; this.cur_accum = paramInt) {
      for (this.cur_bits += this.n_bits; this.cur_bits >= 8; this.cur_bits -= 8)
      {
        char_out((byte)(this.cur_accum & 0xFF), paramOutputStream);
        this.cur_accum >>= 8;
      }
    }
    if ((this.free_ent > this.maxcode) || (this.clear_flg))
    {
      if (!this.clear_flg) {
        break label212;
      }
      int i = this.g_init_bits;
      this.n_bits = i;
      this.maxcode = MAXCODE(i);
      this.clear_flg = false;
    }
    while (paramInt == this.EOFCode)
    {
      for (;;)
      {
        if (this.cur_bits > 0)
        {
          char_out((byte)(this.cur_accum & 0xFF), paramOutputStream);
          this.cur_accum >>= 8;
          this.cur_bits -= 8;
          continue;
          label212:
          this.n_bits += 1;
          if (this.n_bits == this.maxbits)
          {
            this.maxcode = this.maxmaxcode;
            break;
          }
          this.maxcode = MAXCODE(this.n_bits);
          break;
        }
      }
      flush_char(paramOutputStream);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\gifencoder\LZWEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */