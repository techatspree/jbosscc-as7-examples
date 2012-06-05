package clustering.easy;

import javax.ejb.Remote;

@Remote
public interface ClusterMeStatelessRemote
{
	public String whoAreYou();
}
