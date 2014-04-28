package org.rcd.MathGame.util;

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