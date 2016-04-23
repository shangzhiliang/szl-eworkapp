package org.swork.common.utils;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.util.FileCopyUtils;

public class FileUtils
{
  public static void copy(byte[] in, File out)
    throws IOException
  {
    FileCopyUtils.copy(in, out);
  }

  public static byte[] copyToByteArray(File in)
    throws IOException
  {
    return FileCopyUtils.copyToByteArray(in);
  }

  public static String copyToString(File in)
    throws IOException
  {
    return FileCopyUtils.copyToString(new FileReader(in));
  }

  public static int copy(File in, File out)
    throws IOException
  {
    return FileCopyUtils.copy(in, out);
  }
}