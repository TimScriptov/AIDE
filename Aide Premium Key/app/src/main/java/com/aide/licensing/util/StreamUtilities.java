package com.aide.licensing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class StreamUtilities
{
    public static void transfer(Reader source, Writer dest) throws IOException
	{
        char[] buffer = new char[4096];
        while (true)
		{
            try
			{
                int count = source.read(buffer);
                if (count != -1)
				{
                    dest.write(buffer, 0, count);
                }
				else
				{
                    source.close();
                    dest.close();
                    return;
                }
            }
			catch (Throwable th)
			{
                dest.close();
            }
        }
    }

    public static void transfer(InputStream source, OutputStream dest, boolean closeStreams) throws IOException
	{
        byte[] buffer = new byte[4096];
        while (true)
		{
            try
			{
                int count = source.read(buffer);
                if (count == -1)
				{
                    break;
                }
                dest.write(buffer, 0, count);
            }
			catch (Throwable th)
			{
                if (closeStreams)
				{
                    dest.close();
                }
            }
        }
        if (closeStreams)
		{
            source.close();
        }
        if (closeStreams)
		{
            dest.close();
        }
    }

    public static void transfer(InputStream source, OutputStream dest) throws IOException
	{
        transfer(source, dest, true);
    }

    public static String readFully(InputStream stream) throws IOException
	{
        return readFully(new InputStreamReader(stream));
    }

    public static String readFully(Reader reader) throws IOException
	{
        Reader breader = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[8192];
        while (true)
		{
            int read = breader.read(buffer, 0, buffer.length);
            if (read <= 0)
			{
                return builder.toString();
            }
            builder.append(buffer, 0, read);
        }
    }

    public static boolean equals(InputStream stream1, InputStream stream2) throws IOException
	{
        try
		{
            byte[] buf1 = new byte[8192];
            byte[] buf2 = new byte[8192];
            while (true)
			{
                int i1 = readMax(stream1, buf1);
                if (i1 != readMax(stream2, buf2))
				{
                    stream1.close();
                    stream2.close();
                    return false;
                }
				else if (i1 == -1)
				{
                    stream1.close();
                    stream2.close();
                    return true;
                }
				else
				{
                    for (int i = 0; i < i1; i++)
					{
                        if (buf1[i] != buf2[i])
						{
                            break;
                        }
                    }
                }
            }
        }
		finally
		{
            stream1.close();
            stream2.close();
        }
    }

    public static int readMax(InputStream inStream, byte[] arr) throws IOException
	{
        return readMax(inStream, arr, 0, arr.length);
    }

    public static int readMax(InputStream inStream, byte[] arr, int off, int len) throws IOException
	{
        int count = 0;
        while (count < len)
		{
            int currCount = inStream.read(arr, off + count, len - count);
            if (currCount == -1)
			{
                return count > 0 ? count : -1;
            }
			else
			{
                count += currCount;
            }
        }
        return count;
    }
}
