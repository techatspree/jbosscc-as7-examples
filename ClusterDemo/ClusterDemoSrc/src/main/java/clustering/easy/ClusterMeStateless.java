package clustering.easy;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.Clustered;

/**
 * Session Bean implementation class ClusterMeStateless
 */
@Stateless
@Clustered
public class ClusterMeStateless implements ClusterMeStatelessRemote
{
	/**
	 * Default constructor.
	 */
	public ClusterMeStateless()
	{
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
