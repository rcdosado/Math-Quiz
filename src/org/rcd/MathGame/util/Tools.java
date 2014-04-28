package org.rcd.MathGame.util;


import java.io.*;
import java.security.MessageDigest;

public class Tools {

	
	/*
	 * this is slightly modified C++ class from a coder known as yoda
	 * to use this simply do the following:
	 * Tools tools = new Tools();
	 * StopWatch watch = tools.new StopWatch();
	 * watch.Start();
	 */
	public class StopWatch
	{
		long dwStartTime,dwEndTime;
		
		public StopWatch()
		{
			dwStartTime = dwEndTime = 0;
		}
	
		public void Start()
		{
			dwStartTime = System.currentTimeMillis();
			return;
		}
	
		public void Stop()
		{
			dwEndTime = System.currentTimeMillis();
			return;
		}
	
		public long GetMSeconds()
		{
			if ( dwStartTime==0 || dwEndTime==0  )
				return 0; // ERR
	
			return (dwEndTime - dwStartTime);
		}
	
		public long GetSeconds()
		{
			if ( dwStartTime==0 || dwEndTime==0 )
				return 0; // ERR
	
			return (dwEndTime - dwStartTime) / 1000;
		}
	
		public long GetMinutes()
		{
			if ( dwStartTime==0 || dwEndTime==0  )
				return 0; // ERR
	
			return (dwEndTime - dwStartTime) / 60000;
		}
	
		public long GetTimeMSeconds()
		{
			if ( dwStartTime==0 || dwEndTime==0  )
				return 0; // ERR
	
			return (dwEndTime - dwStartTime) % 1000;
		}
	
		public long GetTimeSeconds()
		{
			if ( dwStartTime==0 || dwEndTime==0  )
				return 0; // ERR
	
			return ((dwEndTime - dwStartTime) / 1000) % 60;
		}
	
		public long GetTimeMinutes()
		{
			if ( dwStartTime==0 || dwEndTime==0  )
				return 0; // ERR
	
			return (dwEndTime - dwStartTime) / 60000;
		}
	
	}
	/*
	* @Author - Jeremy Trifilo (Digistr).
	*/
	
	static class ValueFormat
	{
		private static final char[] PREFIXS = {'K', 'M', 'B', 'T'};
	
		public static final byte COMMAS = 0x1;
		public static final byte THOUSANDS = 0x40;
		public static final short MILLIONS = 0x80;
		public static final short BILLIONS = 0xC0;
		public static final short TRILLIONS = 0x100;
	
		public static final int PRECISION(int precision)
		{
			return precision << 2;
		}
	
		public static final int PREFIX(int prefix)
		{
			return prefix << 6;
		}
	
		public static String toString(int settings)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Prefix: ");
			sb.append(settings >> 6 > PREFIXS.length ? PREFIXS.length : settings >> 6);
			sb.append(", Precision: ");
			sb.append((settings >> 2) & 0xF);
			sb.append(", Commas: ");
			sb.append((settings & COMMAS) == COMMAS);
			return sb.toString();
		}
	
		public static String format(long value, int settings)
		{
			StringBuilder sb = new StringBuilder(32);
			sb.append(value);
			char[] data = sb.toString().toCharArray();
			boolean commas = (settings & COMMAS) == COMMAS;
			int precision = 0;
			int prefix = 0;
			if (settings >= 0x40)
			{
				prefix = settings >> 6;
				if (prefix > PREFIXS.length)
					prefix = PREFIXS.length;
			}
			if (settings > COMMAS)
				precision = (settings >> 2) & 0xF;
			sb.setLength(0);
			int negative = 0;
			if (data[0] == '-')
			{
				negative = 1;
			}
			int length = data.length - negative;
			if (prefix * 3 >= length)
			{
				prefix = (int)(length * 0.334);
				if (prefix * 3 == length && precision == 0)
				{
					--prefix;	
				}
			}
			int end = length - (prefix * 3);
			int start = (length % 3);
			if (start == 0)
				start = 3;
			start += negative;
			if (end > 0 && negative == 1)
				sb.append('-');
			int max = end + negative;
			for (int i = negative; i < max; i++)
			{
				if (i == start && i + 2 < max && commas)
				{
					start += 3;
					sb.append(',');
				}
				sb.append(data[i]);
			}
			if (prefix > 0)
			{
				if (end == 0)
				{
					if (negative == 1 && precision > 0)
						sb.append('-');
					sb.append('0');
				}
				max = precision + end + negative;
				if (max > data.length)
					max = data.length;
				end += negative;
				while (max > end)
				{
					if (data[max - 1] == '0')
					{
						--max;
						continue;
					}
					break;
				}
				if ((max - end) != 0)
					sb.append('.');
				for (int i = end; i < max; i++)
				{
					sb.append(data[i]);
				}
				sb.append(PREFIXS[prefix - 1]);
			}
			return sb.toString();
		}
	}
	
	
	/* rip from Bill the Lizard 
	 * http://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
	 */

	static class MD5Checksum {
	
	   public static byte[] createChecksum(String filename) throws Exception {
	       InputStream fis =  new FileInputStream(filename);
	
	       byte[] buffer = new byte[1024];
	       MessageDigest complete = MessageDigest.getInstance("MD5");
	       int numRead;
	
	       do {
	           numRead = fis.read(buffer);
	           if (numRead > 0) {
	               complete.update(buffer, 0, numRead);
	           }
	       } while (numRead != -1);
	
	       fis.close();
	       return complete.digest();
	   }
	
	   // see this How-to for a faster way to convert
	   // a byte array to a HEX string
	   public static String getMD5Checksum(String filename) throws Exception {
	       byte[] b = createChecksum(filename);
	       String result = "";
	
	       for (int i=0; i < b.length; i++) {
	           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
	       }
	       return result;
	   }
	}
	
	
}//end Tools class
