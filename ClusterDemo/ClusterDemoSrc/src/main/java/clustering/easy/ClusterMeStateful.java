package clustering.easy;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.Clustered;

/**
 * Session Bean implementation class ClusterMeStateful
 */
@Stateful
@Clustered
public class ClusterMeStateful implements ClusterMeStatefulRemote
{
	private String _str;
	private int _callCounter;

	/**
	 * Default constructor.
	 */
	public ClusterMeStateful()
	{
		_callCounter = 0;
	}

	@Remove
	@Override
	public void killMe()
	{
	}

	@Override
	public int getCallcounter()
	{
		return _callCounter++;
	}

	@Override
	public String whoAreYou()
	{
		try
		{
			String ret = System.getProperty("jboss.node.name");
			if(ret == null)
			{
				return InetAddress.getLocalHost().getHostName();
			}
			else
			{
				return ret;
			}
		} catch (UnknownHostException e)
		{
			throw new RuntimeException(e);
		}
	}

}
